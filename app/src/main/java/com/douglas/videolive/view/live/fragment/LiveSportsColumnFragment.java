package com.douglas.videolive.view.live.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.douglas.videolive.R;
import com.douglas.videolive.base.BaseFragment;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.logic.live.LiveSportsColumnAllListModelLogic;
import com.douglas.videolive.model.logic.live.bean.LiveSportsAllList;
import com.douglas.videolive.presenter.live.impl.LiveSportsColumnAllListPresenterImp;
import com.douglas.videolive.presenter.live.interfaces.LiveSportsColumnAllListContract;
import com.douglas.videolive.ui.refreshview.XRefreshView;
import com.douglas.videolive.view.live.adapter.LiveSportsColumnListAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：${User}
 * 电话：18810474975
 * 邮箱：18810474975@163.com
 * 版本号：
 * 类描述：
 * 修改时间：${DATA}1634
 */

public class LiveSportsColumnFragment extends BaseFragment<LiveSportsColumnAllListModelLogic,LiveSportsColumnAllListPresenterImp> implements LiveSportsColumnAllListContract.View {

    /**
     *  分页加载
     */
//    起始位置
    private  int offset = 0;
    //    每页加载数量
    private  int limit = 20;
    @BindView(R.id.livesports_content_recyclerview)
    RecyclerView livesportsContentRecyclerview;
    @BindView(R.id.rtefresh_content)
    XRefreshView rtefreshContent;
    private LiveSportsColumnListAdapter mLiveSportsColumnListAdapter;

    public static LiveSportsColumnFragment getInstance() {
        LiveSportsColumnFragment rf = new LiveSportsColumnFragment();
        return rf;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live_sports;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        setXrefeshViewConfig();
        livesportsContentRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
        mLiveSportsColumnListAdapter=new LiveSportsColumnListAdapter(getActivity());
        livesportsContentRecyclerview.setAdapter(mLiveSportsColumnListAdapter);
        rtefreshContent.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
//                延迟500毫秒, 原因 用户体验好 !!!
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh();
                    }
                }, 500);
            }
            @Override
            public void onLoadMore(boolean isSilence) {
                offset+=limit;
                loadMore(offset, limit);
            }
        });
    }
    private void loadMore(int offset, int limit) {
        mPresenter.getPresenterLiveSportsColumnAllListLoadMore(offset,limit);
    }
    /**
     * 刷新网络数据
     */
    private void refresh() {
//       重新开始计算
        offset=0;
        mPresenter.getPresenterLiveSportsColumnAllList(0, limit);
    }

    @Override
    protected void onEvent() {

    }
    /**
     * 配置XRefreshView
     */
    protected void setXrefeshViewConfig() {
        rtefreshContent.setPinnedTime(2000);
        rtefreshContent.setPullLoadEnable(true);
        rtefreshContent.setPullRefreshEnable(true);
        rtefreshContent.setMoveForHorizontal(true);
        rtefreshContent.setPinnedContent(true);
//        滚动到底部 自动加载数据
        rtefreshContent.setSilenceLoadMore();

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
    public void getViewLiveSportsColumnAllListColumn(List<LiveSportsAllList> mLiveAllList) {
                 if(rtefreshContent!=null)
                 {
                     rtefreshContent.stopRefresh();
                 }
            mLiveSportsColumnListAdapter.getLiveLiveSportsColumnList(mLiveAllList);
    }

    @Override
    public void getViewLiveSportsColumnAllListLoadMore(List<LiveSportsAllList> mLiveAllList) {

           if(rtefreshContent!=null)
           {
                rtefreshContent.stopLoadMore();
           }
        mLiveSportsColumnListAdapter.getLiveSportsColumnListLoadMore(mLiveAllList);
    }

    @Override
    public void showErrorWithStatus(String msg) {
    if(rtefreshContent!=null)
    {
          rtefreshContent.stopRefresh(false);
        rtefreshContent.stopLoadMore(false);
    }
    }
}
