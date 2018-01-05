package com.douglas.videolive.presenter.live.impl;


import com.douglas.videolive.model.logic.live.bean.LiveAllList;
import com.douglas.videolive.net.callback.RxSubscriber;
import com.douglas.videolive.net.exception.ResponeThrowable;
import com.douglas.videolive.presenter.live.interfaces.LiveAllListContract;

import java.util.List;

/**
 *  作者：gaoyin
 *  电话：18810474975
 *  邮箱：18810474975@163.com
 *  版本号：1.0
 *  类描述：
 *  备注消息：
 *  修改时间：2017/2/7 下午5:33
 **/
public class LiveAllListPresenterImp extends LiveAllListContract.Presenter {
//     刷新数据
    @Override
    public void getPresenterListAllList(int offset, int limit) {
        addSubscribe(mModel.getModelLiveAllList(mContext,offset,limit).subscribe(new RxSubscriber<List<LiveAllList>>() {
            @Override
            public void onSuccess(List<LiveAllList> mLiveAllList) {
                mView.getViewLiveAllListColumn(mLiveAllList);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
//加载更多
    @Override
    public void getPresenterListAllListLoadMore(int offset, int limit) {
        addSubscribe(mModel.getModelLiveAllList(mContext,offset,limit).subscribe(new RxSubscriber<List<LiveAllList>>() {
            @Override
            public void onSuccess(List<LiveAllList> mLiveAllList) {
                mView.getViewLiveAllListLoadMore(mLiveAllList);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
