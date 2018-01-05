package com.douglas.videolive.presenter.video.interfaces;

import android.content.Context;


import com.douglas.videolive.base.BaseModel;
import com.douglas.videolive.base.BasePresenter;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.logic.video.bean.VideoCateList;

import java.util.List;

import rx.Observable;


public interface VideoAllCateListContract {
    interface  View extends BaseView {
        void getViewVideoAllCate(List<VideoCateList> cateLists);
    }
    interface Model extends BaseModel {
        Observable<List<VideoCateList>> getModelVideoAllCate(Context context);

    }
    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void  getPresenterVideoCatelist();
    }
}
