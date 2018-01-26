package com.douglas.videolive.view.video.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.douglas.videolive.R;
import com.douglas.videolive.base.BaseFragment;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.logic.video.VideoRecommendModelLogic;
import com.douglas.videolive.model.logic.video.bean.VideoHotAuthorColumn;
import com.douglas.videolive.model.logic.video.bean.VideoHotColumn;
import com.douglas.videolive.model.logic.video.bean.VideoRecommendHotCate;
import com.douglas.videolive.presenter.video.impl.VideoRecommendPresenterImp;
import com.douglas.videolive.presenter.video.interfaces.VideoRerecommendContract;
import com.douglas.videolive.ui.refreshview.XRefreshView;
import com.douglas.videolive.view.video.adapter.VideoRecommendAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by shidongfang on 2018/1/24.
 */

public class RecommendVideoFragment extends BaseFragment<VideoRecommendModelLogic,VideoRecommendPresenterImp>
                    implements VideoRerecommendContract.View{

    @BindView(R.id.recommend_content_recyclerview)
    RecyclerView recommendContentRecyclerview;
    @BindView(R.id.rtefresh_content)
    XRefreshView rtefreshContent;

    private SVProgressHUD svProgressHUD;
    private VideoRecommendAdapter mVideoRecommendAdapter;

    public static RecommendVideoFragment getInstance(){
        RecommendVideoFragment rf = new RecommendVideoFragment();
        return rf;
    }
    @Override
    public void showErrorWithStatus(String msg) {
        svProgressHUD.showErrorWithStatus(msg);
        rtefreshContent.stopRefresh(false);
    }

    @Override
    public void getViewHotColumn(List<VideoHotColumn> mVideoHotColumn) {
        if (rtefreshContent != null) {
            rtefreshContent.stopRefresh();
        }
        mVideoRecommendAdapter.getVideoHotColumn(mVideoHotColumn);
    }

    @Override
    public void getViewHotAutherColumn(List<VideoHotAuthorColumn> videoHotAuthorColumns) {
        mVideoRecommendAdapter.getFaceScoreColumn(videoHotAuthorColumns);
    }

    @Override
    public void getViewHotCate(List<VideoRecommendHotCate> videoRecommendHotCates) {
        videoRecommendHotCates.remove(1);
        mVideoRecommendAdapter.getAllColumn(videoRecommendHotCates);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_recommend;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        svProgressHUD = new SVProgressHUD(getActivity());
        recommendContentRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool(){
            @Override
            public void putRecycledView(RecyclerView.ViewHolder scrap) {
                super.putRecycledView(scrap);
            }

            @Override
            public RecyclerView.ViewHolder getRecycledView(int viewType) {
                final RecyclerView.ViewHolder recycledView = super.getRecycledView(viewType);
                return recycledView;
            }
        };
        refresh();
        mVideoRecommendAdapter = new VideoRecommendAdapter(getContext());
        pool.setMaxRecycledViews(mVideoRecommendAdapter.getItemViewType(0),500);
        recommendContentRecyclerview.setRecycledViewPool(pool);
        recommendContentRecyclerview.setAdapter(mVideoRecommendAdapter);
        setXrefreshViewConfig();

    }

    /**
     * 配置xRefreshView
     */
    private void setXrefreshViewConfig() {
        rtefreshContent.setPinnedContent(true);
        rtefreshContent.setPinnedTime(2000);
        rtefreshContent.setPullRefreshEnable(true);
        rtefreshContent.setPullLoadEnable(false);
        rtefreshContent.setMoveForHorizontal(true);
    }

    /**
     * 刷新网络数据
     */
    private void refresh() {
        mPresenter.getPresenterVideoHotColumn();
        mPresenter.getPresenterVideoHotCate();
        mPresenter.getPresenterVideoHotAutherColumn(0,4);

    }

    @Override
    protected void onEvent() {
        rtefreshContent.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
            @Override
            public void onRefresh() {
                //延迟500毫秒
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh();
                    }
                },500);
            }
        });
    }

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    protected void lazyFetchData() {

    }
}
