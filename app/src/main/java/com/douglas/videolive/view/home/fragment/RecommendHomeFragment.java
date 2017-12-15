package com.douglas.videolive.view.home.fragment;

import android.os.Bundle;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.douglas.videolive.R;
import com.douglas.videolive.base.BaseFragment;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.logic.home.HomeRecommondModelLogic;
import com.douglas.videolive.presenter.home.impl.HomeRecommendPresenterImp;

/**
 * Created by shidongfang on 2017/12/13.
 * 推荐页
 */

public class RecommendHomeFragment extends BaseFragment<HomeRecommondModelLogic,HomeRecommendPresenterImp>{
    SVProgressHUD svProgressHUD;

    public static RecommendHomeFragment getInstance(){
        RecommendHomeFragment rf = new RecommendHomeFragment();
        return rf;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_recommend;
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
        return null;
    }

    @Override
    protected void lazyFetchData() {

    }
}
