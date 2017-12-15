package com.douglas.videolive.presenter.home.impl;


import com.douglas.videolive.model.home.bean.HomeCarousel;
import com.douglas.videolive.model.home.bean.HomeFaceScoreColumn;
import com.douglas.videolive.model.home.bean.HomeHotColumn;
import com.douglas.videolive.model.home.bean.HomeRecommendHotCate;
import com.douglas.videolive.net.callback.RxSubscriber;
import com.douglas.videolive.net.exception.ResponeThrowable;
import com.douglas.videolive.presenter.home.interfaces.HomeRecommendContract;

import java.util.List;

/**
 * Created by shidongfang on 2017/12/13.
 * 首页推荐
 */

public class HomeRecommendPresenterImp extends HomeRecommendContract.Presenter {

    /**
     * 轮播图
     */
    @Override
    public void getPresenterCarousel() {
        addSubscribe(mModel.getModelCarousel(mContext).subscribe(new RxSubscriber<List<HomeCarousel>>() {
            @Override
            public void onSuccess(List<HomeCarousel> homeCarousels) {
                mView.getViewCarousel(homeCarousels);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }

    /**
     * 最热
     */
    @Override
    public void getPresenterHotColumn() {
        addSubscribe(mModel.getModelHotColumn(mContext).subscribe(new RxSubscriber<List<HomeHotColumn>>() {
            @Override
            public void onSuccess(List<HomeHotColumn> homeHotColumns) {
                mView.getViewHotColumn(homeHotColumns);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }

    /**
     * 颜值
     *
     * @param offset
     * @param limit
     */
    @Override
    public void getPresenterFaceScoreColumn(int offset, int limit) {
        addSubscribe(mModel.getModelFaceScoreColumn(mContext, offset, limit).subscribe(new RxSubscriber<List<HomeFaceScoreColumn>>() {
            @Override
            public void onSuccess(List<HomeFaceScoreColumn> homeFaceScoreColumns) {
                mView.getViewFaceScoreColumn(homeFaceScoreColumns);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }

    @Override
    public void getPresenterHotCate() {
        addSubscribe(mModel.getModelHotCate(mContext).subscribe(new RxSubscriber<List<HomeRecommendHotCate>>() {
            @Override
            public void onSuccess(List<HomeRecommendHotCate> homeRecommendHotCates) {
                mView.getViewHotcate(homeRecommendHotCates);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
