package com.douglas.videolive.presenter.home.impl;


import com.douglas.videolive.model.home.bean.HomeCateList;
import com.douglas.videolive.net.callback.RxSubscriber;
import com.douglas.videolive.net.exception.ResponeThrowable;
import com.douglas.videolive.presenter.home.HomeCateListContract;

import java.util.List;

/**
 * Created by shidongfang on 2017/12/8.
 **/
public class HomeCateListPresenterImp extends HomeCateListContract.Presenter {

    @Override
    public void getHomeCateList1() {
        addSubscribe(mModel.getHomeCateList(mContext).subscribe(new RxSubscriber<List<HomeCateList>>() {
            @Override
            public void onSuccess(List<HomeCateList> homeCateListList) {
                mView.getHomeAllList(homeCateListList);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
