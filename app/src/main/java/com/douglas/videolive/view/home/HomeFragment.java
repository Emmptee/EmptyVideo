package com.douglas.videolive.view.home;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.douglas.videolive.R;
import com.douglas.videolive.base.BaseFragment;
import com.douglas.videolive.base.BaseView;
import com.flyco.tablayout.SlidingTabLayout;

import butterknife.BindView;

/**
 * Created by shidongfang on 2017/12/7.
 */

public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";

    SVProgressHUD svProgressHUD;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.img_scanner)
    ImageView imgScanner;
    @BindView(R.id.img_history)
    ImageView imgHistory;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.sliding_tab)
    SlidingTabLayout slidingTab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
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

    }
}
