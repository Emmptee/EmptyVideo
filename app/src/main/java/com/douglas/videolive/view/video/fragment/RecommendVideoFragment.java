package com.douglas.videolive.view.video.fragment;

import android.os.Bundle;
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

    public static RecommendVideoFragment getInstance(){
        RecommendVideoFragment rf = new RecommendVideoFragment();
        return rf;
    }
    @Override
    public void showErrorWithStatus(String msg) {

    }

    @Override
    public void getViewHotColumn(List<VideoHotColumn> mVideoHotColumn) {

    }

    @Override
    public void getViewHotAutherColumn(List<VideoHotAuthorColumn> videoHotAuthorColumns) {

    }

    @Override
    public void getViewHotCate(List<VideoRecommendHotCate> videoRecommendHotCates) {

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
        new VideoRecommendAdapter()

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

    }

    @Override
    protected BaseView getViewImp() {
        return null;
    }

    @Override
    protected void lazyFetchData() {

    }
}
