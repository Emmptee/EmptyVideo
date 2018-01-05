package com.douglas.videolive.view.live.fragment;

import android.os.Bundle;

import com.douglas.videolive.R;
import com.douglas.videolive.base.BaseFragment;
import com.douglas.videolive.base.BaseView;

public class LiveCommonColumnFragment extends BaseFragment {

    public static LiveCommonColumnFragment getInstance() {
        LiveCommonColumnFragment rf = new LiveCommonColumnFragment();
        return rf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live_commoncolumn;
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
}
