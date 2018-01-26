package com.douglas.videolive.view.video.fragment;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by shidongfang on 2018/1/26.
 */

public class VideoOtherTwoColumnFragment extends BaseFragment<VideoTwoColumnModelLogic,VideoOtherTwoColumnPresenterImp>
                implements VideoOtherTwoColumnContract.View{
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
    private VideoOtherColumnListAdapter

    public static VideoOtherTwoColumnFragment getInstance(){
        VideoOtherTwoColumnFragment videoOtherTwoColumnFragment = new VideoOtherTwoColumnFragment();
        return videoOtherTwoColumnFragment;
    }

    public static VideoOtherTwoColumnFragment getInstance(VideoReClassify videoReClassify,int position){
        VideoOtherTwoColumnFragment videoOtherTwoColumnFragment = new VideoOtherTwoColumnFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("mVideoOtherTwoColumn",videoReClassify);
        bundle.putInt("position",position);
        mVideoOtherTwoColumnFragment.add(position,videoOtherTwoColumnFragment);
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
        return null;
    }

    @Override
    protected void lazyFetchData() {

    }

    @Override
    public void showErrorWithStatus(String msg) {

    }

    @Override
    public void getViewVideoOtherColumnListLoadMore(List<VideoOtherColumnList> mVideoOtherColumnList) {

    }

    @Override
    public void getViewOtherTwoColumn(List<VideoOtherColumnList> mVideoReClassify) {

    }
}
