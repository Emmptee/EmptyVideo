package com.douglas.videolive.view.live.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.douglas.videolive.R;
import com.douglas.videolive.base.BaseFragment;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.logic.live.LiveOtherColumn;
import com.douglas.videolive.model.logic.live.LiveOtherColumnModelLogic;
import com.douglas.videolive.presenter.live.impl.LiveOtherColumnPresenterImp;
import com.douglas.videolive.presenter.live.interfaces.LiveOtherColumnContract;
import com.douglas.videolive.view.live.adapter.LiveAllCloumnAdapter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shidongfang on 2017/12/7.
 */

public class LiveFragment extends BaseFragment<LiveOtherColumnModelLogic, LiveOtherColumnPresenterImp>
        implements LiveOtherColumnContract.View {


    private SVProgressHUD svProgressHUD;
    @BindView(R.id.live_sliding_tab)
    SlidingTabLayout liveSlidingTab;
    @BindView(R.id.live_viewpager)
    ViewPager liveViewPager;
    private String[] mTitle;
    private LiveAllCloumnAdapter mLiveAllCloumnAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =super.onCreateView(inflater,container,savedInstanceState);
        ButterKnife.bind(this.rootView);
        return rootView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live;
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
        mPresenter.getPresenterLiveOtherColumn();
    }

    @Override
    public void showErrorWithStatus(String msg) {

    }

    @Override
    public void getViewLiveOtherColumn(List<LiveOtherColumn> mLiveOtherColumns) {
        mTitle = new String[mLiveOtherColumns.size() + 3];
        mTitle[0] ="常用";
        mTitle[1] = "全部";
        for (int i = 0; i < mLiveOtherColumns.size(); i++) {
            mTitle[i+2] = mLiveOtherColumns.get(i).getCate_name();
        }
        mTitle[mTitle.length-1] = "体育直播";
        liveViewPager.setOffscreenPageLimit(mTitle.length);
        mLiveAllCloumnAdapter = new LiveAllCloumnAdapter(getChildFragmentManager(), mLiveOtherColumns, mTitle);
        liveViewPager.setAdapter(mLiveAllCloumnAdapter);
        mLiveAllCloumnAdapter.notifyDataSetChanged();
        liveSlidingTab.setViewPager(liveViewPager,mTitle);
        liveSlidingTab.setCurrentTab(1);

    }
}
