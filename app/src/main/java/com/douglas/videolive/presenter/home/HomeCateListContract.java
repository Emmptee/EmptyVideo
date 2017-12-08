package com.douglas.videolive.presenter.home;

import android.content.Context;

import com.douglas.videolive.base.BaseModel;
import com.douglas.videolive.base.BasePresenter;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.home.bean.HomeCateList;

import java.util.List;

import rx.Observable;

/**
 * Created by shidongfang on 2017/12/8.
 */

public interface HomeCateListContract {
    interface View extends BaseView{
        void getHomeAllList(List<HomeCateList> cateLists);
    }

    interface Model extends BaseModel{
        Observable getHomeCateList(Context context);

    }

    abstract  class Presenter extends BasePresenter<View,Model>{
        public abstract void getHomeCateList1();
    }
}
