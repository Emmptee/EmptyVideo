package com.douglas.videolive.presenter.video.interfaces;

import android.content.Context;


import com.douglas.videolive.base.BaseModel;
import com.douglas.videolive.base.BasePresenter;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.logic.video.bean.VideoReClassify;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public interface VideoOtherCateContract {
    interface View extends BaseView {
        void getViewVideoOtherCate(List<VideoReClassify> cateLists);
    }

    interface Model extends BaseModel {
        Observable<List<VideoReClassify>> getModelVideoAllCate(Context context, String cId);
    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getPresenterVideoOtherCate(String cid);

    }
}
