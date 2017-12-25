package com.douglas.videolive.view.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.douglas.videolive.R;
import com.douglas.videolive.base.BaseFragment;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.home.bean.HomeCarousel;
import com.douglas.videolive.model.home.bean.HomeFaceScoreColumn;
import com.douglas.videolive.model.home.bean.HomeHotColumn;
import com.douglas.videolive.model.home.bean.HomeRecommendHotCate;
import com.douglas.videolive.model.logic.home.HomeRecommondModelLogic;
import com.douglas.videolive.presenter.home.impl.HomeRecommendPresenterImp;
import com.douglas.videolive.presenter.home.interfaces.HomeRecommendContract;
import com.douglas.videolive.ui.refreshview.XRefreshView;
import com.douglas.videolive.view.common.activity.PcLiveVideoActivity;
import com.douglas.videolive.view.home.adapter.HomeCarouselAdapter;
import com.douglas.videolive.view.home.adapter.HomeRecommendAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by shidongfang on 2017/12/13.
 * 推荐页
 */

public class RecommendHomeFragment extends BaseFragment<HomeRecommondModelLogic, HomeRecommendPresenterImp>
                        implements HomeRecommendContract.View,BGABanner.Delegate<SimpleDraweeView,String>{
    SVProgressHUD svProgressHUD;
    @BindView(R.id.refresh_content)
    XRefreshView refreshContent;
    @BindView(R.id.recommond_content_recyclerview)
    RecyclerView recommend_recyclerView;
    private HomeRecommendAdapter mAdapter;
    private List<HomeCarousel> mHomeCarousel;
    private HomeCarouselAdapter mRecommendBannerAdapter;
    private View headerView;
    private BGABanner recommend_banner;

    public static RecommendHomeFragment getInstance() {
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
        recommend_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new HomeRecommendAdapter(getContext());
        mHomeCarousel = new ArrayList<HomeCarousel>();
        pool.setMaxRecycledViews(mAdapter.getAdapterItemViewType(0),500);
        recommend_recyclerView.setRecycledViewPool(pool);
        recommend_recyclerView.setAdapter(mAdapter);
        mRecommendBannerAdapter = new HomeCarouselAdapter();
        headerView = mAdapter.setHeaderView(R.layout.item_home_recommend_banner, recommend_recyclerView);
        recommend_banner = (BGABanner) headerView.findViewById(R.id.recommed_banner);
        recommend_banner.setDelegate(this);
        recommend_banner.setAdapter(mRecommendBannerAdapter);
        setXrefreshViewConfig();
    }

    /**
     * 配置xRefreshViewConfig
     */
    private void setXrefreshViewConfig() {
        refreshContent.setPinnedTime(2000);
        refreshContent.setPinnedContent(true);
        refreshContent.setPullLoadEnable(false);
        refreshContent.setPullRefreshEnable(true);
        refreshContent.setMoveForHorizontal(true);
    }

    final RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool() {
        @Override
        public void putRecycledView(RecyclerView.ViewHolder scrap) {
            super.putRecycledView(scrap);
        }

        @Override
        public RecyclerView.ViewHolder getRecycledView(int viewType) {
            final RecyclerView.ViewHolder recycledView = super.getRecycledView(viewType);
            return recycledView;
        }
    };

    @Override
    protected void onEvent() {
        refreshContent.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh();
                    }
                },500);
            }
        });
    }

    /**
     * 刷新网络数据
     */
    private void refresh() {
        mPresenter.getPresenterCarousel();
        mPresenter.getPresenterHotColumn();
        mPresenter.getPresenterFaceScoreColumn(0,4);
        mPresenter.getPresenterHotCate();
    }

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    protected void lazyFetchData() {
        refresh();
    }

    @Override
    public void onBannerItemClick(BGABanner banner, SimpleDraweeView itemView, String model, int position) {
        Intent intent = new Intent(getActivity(), PcLiveVideoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("romm_id",mHomeCarousel.get(position).getRoom().getRoom_id());
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }

    @Override
    public void showErrorWithStatus(String msg) {
        svProgressHUD.showErrorWithStatus(msg);
        refreshContent.stopRefresh(false);
    }

    /**
     * 轮播图
     * @param homeCarousel
     */
    @Override
    public void getViewCarousel(List<HomeCarousel> homeCarousel) {
        if (refreshContent != null) {
            refreshContent.stopRefresh();
        }
        this.mHomeCarousel.clear();
        this.mHomeCarousel.addAll(mHomeCarousel);
        ArrayList<String> pic_url = new ArrayList<>();
        for (int i = 0; i < mHomeCarousel.size(); i++) {
            pic_url.add(homeCarousel.get(i).getPic_url());
        }
        if (recommend_banner != null && pic_url.size() >0) {
            recommend_banner.setData(R.layout.item_image_carousel,pic_url,null);
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     *最热
     * @param homeHotColumn
     */
    @Override
    public void getViewHotColumn(List<HomeHotColumn> homeHotColumn) {
        mAdapter.getHomeHotColumn(homeHotColumn);
    }

    /**
     * 颜值
     * @param homeFaceScoreColumn
     */
    @Override
    public void getViewFaceScoreColumn(List<HomeFaceScoreColumn> homeFaceScoreColumn) {
        mAdapter.getFaceScoreColumn(homeFaceScoreColumn);
    }

    /**
     * 热门
     * @param homeRecommendHotCates
     */
    @Override
    public void getViewHotcate(List<HomeRecommendHotCate> homeRecommendHotCates) {
        homeRecommendHotCates.remove(1);
        mAdapter.getAllColumn(homeRecommendHotCates);
    }
}
