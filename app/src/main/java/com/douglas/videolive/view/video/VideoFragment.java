package com.douglas.videolive.view.video;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.douglas.videolive.R;
import com.douglas.videolive.base.BaseFragment;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.logic.video.VideoCateListLogic;
import com.douglas.videolive.model.logic.video.bean.VideoCateList;
import com.douglas.videolive.presenter.video.impl.VideoCateListPresenterImpl;
import com.douglas.videolive.presenter.video.interfaces.VideoAllCateListContract;
import com.douglas.videolive.view.video.adapter.VideoAllListAdapter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.List;

import butterknife.BindView;

/**
 * Created by shidongfang on 2017/12/7.
 */

public class VideoFragment extends BaseFragment<VideoCateListLogic,VideoCateListPresenterImpl>
                        implements VideoAllCateListContract.View{


    private SVProgressHUD mSvProgressHUD;
    private String[] mTitles;
    @BindView(R.id.live_sliding_tab)
    SlidingTabLayout liveSlideingTab;
    @BindView(R.id.live_viewpager)
    ViewPager liveViewpager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        mSvProgressHUD = new SVProgressHUD(getActivity());
        mPresenter.getPresenterVideoCatelist();
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
        mPresenter.getPresenterVideoCatelist();
    }

    @Override
    public void showErrorWithStatus(String msg) {
        mSvProgressHUD.showErrorWithStatus(msg);
    }

    @Override
    public void getViewVideoAllCate(List<VideoCateList> cateLists) {
        /**默认数据
        * */
        mTitles = new String[cateLists.size() + 1];
        mTitles[0] = "推荐";
        for (int i = 0; i < cateLists.size(); i++) {
            mTitles[i+1] = cateLists.get(i).getCate1_name();
        }
        //不摧毁fragment
        liveViewpager.setOffscreenPageLimit(mTitles.length);
        VideoAllListAdapter videoAllListAdapter = new VideoAllListAdapter(getChildFragmentManager(),cateLists,mTitles);
        liveViewpager.setAdapter(videoAllListAdapter);
        videoAllListAdapter.notifyDataSetChanged();
        liveSlideingTab.setViewPager(liveViewpager,mTitles);
    }
}
