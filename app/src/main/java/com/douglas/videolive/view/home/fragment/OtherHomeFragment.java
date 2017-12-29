package com.douglas.videolive.view.home.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.douglas.videolive.base.BaseFragment;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.home.bean.HomeRecommendHotCate;
import com.douglas.videolive.model.logic.home.HomeCateModelLogic;
import com.douglas.videolive.presenter.home.impl.HomeCatePresenterImp;
import com.douglas.videolive.presenter.home.interfaces.HomeCateContract;

import java.util.List;

/**
 * 导航栏、分页
 * Created by shidongfang on 2017/12/29.
 */

public class OtherHomeFragment extends BaseFragment<HomeCateModelLogic,HomeCatePresenterImp>
implements HomeCateContract.View,ViewPager.OnPageChangeListener{

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onInitView(Bundle bundle) {

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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void showErrorWithStatus(String msg) {

    }

    @Override
    public void getOtherList(List<HomeRecommendHotCate> homeCates) {

    }

    @Override
    public void getOtherListRefresh(List<HomeRecommendHotCate> homeCates) {

    }


}
