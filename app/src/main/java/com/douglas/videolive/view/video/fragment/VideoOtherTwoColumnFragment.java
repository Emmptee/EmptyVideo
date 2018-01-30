package com.douglas.videolive.view.video.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.douglas.videolive.R;
import com.douglas.videolive.base.BaseFragment;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.logic.video.VideoTwoColumnModelLogic;
import com.douglas.videolive.model.logic.video.bean.VideoOtherColumnList;
import com.douglas.videolive.model.logic.video.bean.VideoReClassify;
import com.douglas.videolive.presenter.video.impl.VideoOtherTwoColumnPresenterImp;
import com.douglas.videolive.presenter.video.interfaces.VideoOtherTwoColumnContract;
import com.douglas.videolive.ui.refreshview.XRefreshView;
import com.douglas.videolive.view.home.adapter.FullyGridLayoutManager;
import com.douglas.videolive.view.video.adapter.VideoOtherColumnListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by shidongfang on 2018/1/26.
 */

public class VideoOtherTwoColumnFragment extends BaseFragment<VideoTwoColumnModelLogic, VideoOtherTwoColumnPresenterImp>
        implements VideoOtherTwoColumnContract.View {
    //起始位置
    private int offSet = 0;
    //每页加载数量
    private int limit = 20;
    private static List<VideoOtherTwoColumnFragment>
            mVideoOtherTwoColumnFragment = new ArrayList<VideoOtherTwoColumnFragment>();
    @BindView(R.id.other_content_recyclerview)
    RecyclerView otherColumnContentRecyclerview;
    @BindView(R.id.rtefresh_content)
    XRefreshView rtefreshContent;
    private VideoReClassify mLiveOtherTwoColumn;
    private VideoOtherColumnListAdapter mVideoOtherColumnListAdapter;

    public static VideoOtherTwoColumnFragment getInstance() {
        VideoOtherTwoColumnFragment videoOtherTwoColumnFragment = new VideoOtherTwoColumnFragment();
        return videoOtherTwoColumnFragment;
    }

    public static VideoOtherTwoColumnFragment getInstance(VideoReClassify videoReClassify, int position) {
        VideoOtherTwoColumnFragment videoOtherTwoColumnFragment = new VideoOtherTwoColumnFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("mVideoOtherTwoColumn", videoReClassify);
        bundle.putInt("position", position);
        mVideoOtherTwoColumnFragment.add(position, videoOtherTwoColumnFragment);
        videoOtherTwoColumnFragment.setArguments(bundle);
        return videoOtherTwoColumnFragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live_othercolumn_list;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        setXrefreshViewConfig();
        otherColumnContentRecyclerview.setLayoutManager(new FullyGridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
        Bundle arguments = getArguments();
        mLiveOtherTwoColumn = (VideoReClassify) arguments.getSerializable("mVideoOtherTwo");
        mVideoOtherColumnListAdapter = new VideoOtherColumnListAdapter(getActivity());
        otherColumnContentRecyclerview.setAdapter(mVideoOtherColumnListAdapter);
        rtefreshContent.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh(mLiveOtherTwoColumn.getCid1(), mLiveOtherTwoColumn.getCid2(), offSet, limit, "hot");
                    }
                }, 500);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                offSet += limit;
                loadMore(mLiveOtherTwoColumn.getCid1(), mLiveOtherTwoColumn.getCid2(), offSet, limit, "hot");
            }
        });

    }

    private void loadMore(String cid1, String cid2, int offset, int limit, String action) {
        mPresenter.getPresenterLiveOtherColumnListLoadMore(cid1, cid2, offset, limit, action);
    }

    /**
     * 刷新网络数据
     */
    private void refresh(String cid1, String cid2, int offset, int limit, String action) {
//       重新开始计算
        offset = 0;
        mPresenter.getPresenterLiveOtherColumnList(cid1, cid2, offset, limit, action);

    }

    /**
     *
     */
    private void setXrefreshViewConfig() {
        rtefreshContent.setPinnedTime(2000);
        rtefreshContent.setPullLoadEnable(true);
        rtefreshContent.setPullRefreshEnable(true);
        rtefreshContent.setMoveForHorizontal(true);
        rtefreshContent.setPinnedContent(true);
//        滚动到底部 自动加载数据
        rtefreshContent.setSilenceLoadMore();
    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected BaseView getViewImp() {
        Bundle arguments = getArguments();
        return mVideoOtherTwoColumnFragment.get(arguments.getInt("position"));

    }

    @Override
    protected void lazyFetchData() {
        mLiveOtherTwoColumn = new VideoReClassify();
        Bundle arguments = getArguments();
        mLiveOtherTwoColumn = (VideoReClassify) arguments.getSerializable("mVideoOtherTwoColumn");
        refresh(mLiveOtherTwoColumn.getCid1(),mLiveOtherTwoColumn.getCid2(),offSet,limit,"hot");

    }

    @Override
    public void showErrorWithStatus(String msg) {
        if (rtefreshContent != null) {
            rtefreshContent.stopRefresh();
            rtefreshContent.stopLoadMore();
        }
    }

    @Override
    public void getViewVideoOtherColumnListLoadMore(List<VideoOtherColumnList> mVideoOtherColumnList) {
        if (rtefreshContent != null) {
            rtefreshContent.stopLoadMore();

        }
        mVideoOtherColumnListAdapter.getLiveOtherColumnLoadMore(mVideoOtherColumnList);
    }

    @Override
    public void getViewOtherTwoColumn(List<VideoOtherColumnList> mVideoReClassify) {
        if (rtefreshContent != null) {
            rtefreshContent.stopRefresh();
        }
        mVideoOtherColumnListAdapter.getLiveOtherColumnList(mVideoReClassify);
    }
}
