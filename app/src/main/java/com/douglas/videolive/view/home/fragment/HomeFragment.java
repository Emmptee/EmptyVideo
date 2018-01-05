package com.douglas.videolive.view.home.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.douglas.videolive.R;
import com.douglas.videolive.base.BaseFragment;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.home.HomeCateListModelLogic;
import com.douglas.videolive.model.home.bean.HomeCateList;
import com.douglas.videolive.presenter.home.HomeCateListContract;
import com.douglas.videolive.presenter.home.impl.HomeCateListPresenterImp;
import com.douglas.videolive.ui.pagestatemanager.PageManager;
import com.douglas.videolive.view.home.adapter.HomeAllListAdapter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment<HomeCateListModelLogic, HomeCateListPresenterImp> implements HomeCateListContract
        .View {
    SVProgressHUD svProgressHUD;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.img_scanner)
    ImageView imgScanner;
    @BindView(R.id.img_history)
    ImageView imgHistory;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    Handler handler = new Handler();
    @BindView(R.id.sliding_tab)
    SlidingTabLayout slidingTab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private PageManager pageStateManager;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private HomeAllListAdapter mAdapter;
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

//    @OnClick(R.id.btn_home)
//    public void home() {
////        正常数据
////     mPresenter.getHomeCate("3e760da75be261a588c74c4830632360");
////        错误数据
////        mPresenter.getHomeCate("3e760da75be261a588c74c483063236");
//
//
////        初始化pageStateManager
//        pageStateManager = PageManager.init(this, true, () -> Toast.makeText(getActivity().getApplicationContext(),
//                "点击重试了...", Toast
//                        .LENGTH_LONG).show());
//        handler.postDelayed(() -> {
////            修改state值来查看不同状态
//            int state = 1;
//            switch (state) {
//                case 0:
//                    pageStateManager.showError();
//                    break;
//                case 1:
//                    pageStateManager.showEmpty();
//                    break;
//                case 2:
//                    pageStateManager.showContent();
//            }
//
//        }, 2000);
//    }

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
    public void getHomeAllList(List<HomeCateList> cateLists) {
        /**
         *  默认数据
         */
        mTitles = new String[cateLists.size()+1];
        mTitles[0]="推荐";
//        mFragments.add(RecommendHomeFragment.getInstance());
        for (int i=0;i<cateLists.size();i++)
        {
            mTitles[i+1]=cateLists.get(i).getTitle();
//            Bundle bundle=new Bundle();
//             bundle.putSerializable("homecatelist",cateLists.get(i));
//            mFragments.add(OtherHomeFragment.getInstance(bundle));
        }
//        不摧毁Fragment
        viewpager.setOffscreenPageLimit(mTitles.length);
        mAdapter = new HomeAllListAdapter(getChildFragmentManager(),cateLists,mTitles);
        viewpager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        slidingTab.setViewPager(viewpager,mTitles);
    }
    @Override
    public void showErrorWithStatus(String msg) {
        svProgressHUD.showErrorWithStatus(msg);
    }

}
