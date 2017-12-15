package com.douglas.videolive.presenter.home.interfaces;

import android.content.Context;

import com.douglas.videolive.base.BaseModel;
import com.douglas.videolive.base.BasePresenter;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.home.bean.HomeCarousel;
import com.douglas.videolive.model.home.bean.HomeFaceScoreColumn;
import com.douglas.videolive.model.home.bean.HomeHotColumn;
import com.douglas.videolive.model.home.bean.HomeRecommendHotCate;

import java.util.List;

import rx.Observable;

/**
 * Created by shidongfang on 2017/12/13.
 */

public interface HomeRecommendContract {
    interface View extends BaseView {
        //轮播图
        void getViewCarousel(List<HomeCarousel> homeCarousel);

        //最热栏目
        void getViewHotColumn(List<HomeHotColumn> homeHotColumn);

        //颜值栏目
        void getViewFaceScoreColumn(List<HomeFaceScoreColumn> homeFaceScoreColumn);

        //热门栏目
        void getViewHotcate(List<HomeRecommendHotCate> homeRecommendHotCates);
    }

    interface Model extends BaseModel {
        Observable<List<HomeCarousel>> getModelCarousel(Context context);

        Observable<List<HomeHotColumn>> getModelHotColumn(Context context);

        Observable<List<HomeFaceScoreColumn>> getModelFaceScoreColumn(Context context,int offset,int limit);

        Observable<List<HomeRecommendHotCate>> getModelHotCate(Context context);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        //轮播
        public abstract void getPresenterCarousel();
        //最热栏目
        public abstract void getPresenterHotColumn();
        //颜值
        public abstract void getPresenterFaceScoreColumn(int offset,int limit);
        //热门
        public abstract void getPresenterHotCate();
    }
}
