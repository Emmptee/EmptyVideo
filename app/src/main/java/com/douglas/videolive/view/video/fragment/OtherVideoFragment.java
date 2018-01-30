package com.douglas.videolive.view.video.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.douglas.videolive.R;
import com.douglas.videolive.base.BaseFragment;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.logic.video.VideoOtherCateListLogic;
import com.douglas.videolive.model.logic.video.bean.VideoCateList;
import com.douglas.videolive.model.logic.video.bean.VideoReClassify;
import com.douglas.videolive.presenter.video.impl.VideoOtherCatePresenterImpl;
import com.douglas.videolive.presenter.video.interfaces.VideoOtherCateContract;
import com.douglas.videolive.view.video.adapter.videoReClassifyListAdapter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by shidongfang on 2018/1/26.
 * 视频页 列表页  显示 手游,娱乐,游戏,趣玩等
 */

public class OtherVideoFragment extends BaseFragment<VideoOtherCateListLogic, VideoOtherCatePresenterImpl>
        implements VideoOtherCateContract.View {

    @BindView(R.id.twocolumn_tablayout)
    SlidingTabLayout liveSlidingTab;
    @BindView(R.id.live_viewpager)
    ViewPager liveViewPaper;

    private String[] mTitles;
    private videoReClassifyListAdapter mAdapter;
    private static List<OtherVideoFragment> mVideoOtherColumnFragment = new ArrayList<OtherVideoFragment>();
    private SVProgressHUD svProgressHUD;
    private VideoCateList mVideoOtherColumn;

    public static OtherVideoFragment getInstance(VideoCateList videoCateList, int position) {
        OtherVideoFragment rf = new OtherVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("mVideoCatelist", videoCateList);
        bundle.putInt("position", position);
        mVideoOtherColumnFragment.add(position, rf);
        rf.setArguments(bundle);
        return rf;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_other;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        svProgressHUD = new SVProgressHUD(getActivity());
    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    protected void lazyFetchData() {
        Bundle arguments = getArguments();
        mVideoOtherColumn = (VideoCateList) arguments.getSerializable("mVideoCateList");
        mPresenter.getPresenterVideoOtherCate(mVideoOtherColumn.getCid1());
    }

    @Override
    public void showErrorWithStatus(String msg) {

    }

    @Override
    public void getViewVideoOtherCate(List<VideoReClassify> cateLists) {
        mTitles = new String[cateLists.size()];
        for (int i = 0; i < cateLists.size(); i++) {
            mTitles[i] = cateLists.get(i).getCate2_name();
            if (mTitles.length < 1){
                liveSlidingTab.setVisibility(View.GONE);
            }

            liveViewPaper.setOffscreenPageLimit(mTitles.length);
            mAdapter = new videoReClassifyListAdapter(getChildFragmentManager(),cateLists,mTitles);
            liveViewPaper.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            liveSlidingTab.setViewPager(liveViewPaper,mTitles);
        }
    }
}
