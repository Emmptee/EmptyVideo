package com.douglas.videolive.view.home;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Size;
import android.widget.ImageView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.douglas.videolive.R;
import com.douglas.videolive.base.BaseFragment;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.home.HomeCateListModelLogic;
import com.douglas.videolive.model.home.bean.HomeCateList;
import com.douglas.videolive.presenter.home.HomeCateListContract;
import com.douglas.videolive.presenter.home.impl.HomeCateListPresenterImp;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shidongfang on 2017/12/7.
 */

public class HomeFragment extends BaseFragment<HomeCateListModelLogic, HomeCateListPresenterImp>
        implements HomeCateListContract.View {

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
    private String[] mTitles;

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
        mPresenter.getHomeCateList();
    }

    @OnClick(R.id.img_message)
    public void message() {

    }

    @OnClick(R.id.img_history)
    public void history() {

    }

    @OnClick(R.id.img_scanner)
    public void scanner() {

    }

    @OnClick(R.id.img_search)
    public void search() {

    }

    @Override
    public void showErrorWithStatus(String msg) {

    }

    @Override
    public void getHomeAllList(List<HomeCateList> cateLists) {
        mTitles = new String[cateLists.size() + 1];
        mTitles[0] ="推荐";
        for (int i = 0; i < cateLists.size(); i++) {
            mTitles[i+1] = cateLists.get(i).getTitle();
        }
        //不销毁fragment
        viewPager.setOffscreenPageLimit(mTitles.length);
        new homeAll
    }
}
