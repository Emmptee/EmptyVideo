package com.douglas.videolive.presenter.live.impl;


import com.douglas.videolive.model.logic.live.bean.LiveOtherTwoColumn;
import com.douglas.videolive.net.callback.RxSubscriber;
import com.douglas.videolive.net.exception.ResponeThrowable;
import com.douglas.videolive.presenter.live.interfaces.LiveOtherTwoColumnContract;

import java.util.List;

/**
 *  作者：gaoyin
 *  电话：18810474975
 *  邮箱：18810474975@163.com
 *  版本号：1.0
 *  类描述：
 *  备注消息：
 *  修改时间：2016/11/14 下午3:22
 **/
public class LiveOtherTwoColumnPresenterImp extends LiveOtherTwoColumnContract.Presenter {

    @Override
    public void getPresenterLiveOtherTwoColumn(String mCloumnName) {
        addSubscribe(mModel.getModelLiveOtherTwoColumn(mContext,mCloumnName).subscribe(new RxSubscriber<List<LiveOtherTwoColumn>>() {
            @Override
            public void onSuccess(List<LiveOtherTwoColumn> mLiveOtherTwoColumns) {
                mView.getViewLiveOtherTwoColumn(mLiveOtherTwoColumns);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
