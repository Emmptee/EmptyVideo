package com.douglas.videolive.presenter.live.interfaces;


import android.content.Context;

import com.douglas.videolive.base.BaseModel;
import com.douglas.videolive.base.BasePresenter;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.logic.live.LiveOtherColumn;

import java.util.List;

import rx.Observable;

public interface LiveOtherColumnContract {
      interface View extends BaseView {
         void   getViewLiveOtherColumn(List<LiveOtherColumn> mLiveOtherColumns);
      }
      interface Model extends BaseModel {
            Observable<List<LiveOtherColumn>> getModelLiveOtherColumn(Context context);
      }
      abstract class Presenter extends BasePresenter<View,Model> {
//            获取直播其他栏目分类
            public abstract void  getPresenterLiveOtherColumn();

      }

}
