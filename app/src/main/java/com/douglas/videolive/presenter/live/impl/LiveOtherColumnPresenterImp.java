package com.douglas.videolive.presenter.live.impl;


import com.douglas.videolive.model.logic.live.LiveOtherColumn;
import com.douglas.videolive.net.callback.RxSubscriber;
import com.douglas.videolive.net.exception.ResponeThrowable;
import com.douglas.videolive.presenter.live.interfaces.LiveOtherColumnContract;

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
public class LiveOtherColumnPresenterImp extends LiveOtherColumnContract.Presenter {

    @Override
    public void getPresenterLiveOtherColumn() {
        addSubscribe(mModel.getModelLiveOtherColumn(mContext).subscribe(new RxSubscriber<List<LiveOtherColumn>>() {
            @Override
            public void onSuccess(List<LiveOtherColumn> mLiveOtherColumns) {
                mView.getViewLiveOtherColumn(mLiveOtherColumns);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
