package com.douglas.videolive.presenter.home.impl;


import com.douglas.videolive.model.home.bean.HomeRecommendHotCate;
import com.douglas.videolive.net.callback.RxSubscriber;
import com.douglas.videolive.net.exception.ResponeThrowable;
import com.douglas.videolive.presenter.home.interfaces.HomeCateContract;

import java.util.List;

/**

 **/
public class HomeCatePresenterImp extends HomeCateContract.Presenter {
    /**
     * 导航栏+栏目列表
     *
     * @param identification
     */
    @Override
    public void getHomeCate(String identification) {
        addSubscribe(mModel.getHomeCate(mContext, identification).subscribe(new RxSubscriber<List<HomeRecommendHotCate>>() {
            @Override
            public void onSuccess(List<HomeRecommendHotCate> homeCates) {
                mView.getOtherList(homeCates);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }

    /**
     * 刷新
     * <p></p>
     * 导航栏+栏目列表
     *
     * @param identification
     */
    @Override
    public void getHomeCateRefresh(String identification) {
        addSubscribe(mModel.getHomeCate(mContext, identification).subscribe(new RxSubscriber<List<HomeRecommendHotCate>>() {
            @Override
            public void onSuccess(List<HomeRecommendHotCate> homeCates) {
                mView.getOtherListRefresh(homeCates);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
