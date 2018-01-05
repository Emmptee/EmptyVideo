package com.douglas.videolive.presenter.common.interfaces;


import android.content.Context;


import com.douglas.videolive.base.BaseModel;
import com.douglas.videolive.base.BasePresenter;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.logic.common.bean.TempLiveVideoInfo;

import okhttp3.Request;

/**
 *  作者：gaoyin
 *  电话：18810474975
 *  邮箱：18810474975@163.com
 *  版本号：1.0
 *  类描述：
 *  备注消息：
 *  修改时间：2016/11/14 下午3:29
 **/
public interface CommonPhoneLiveVideoContract {

    interface View extends BaseView {
        void getViewPhoneLiveVideoInfo(TempLiveVideoInfo mLiveVideoInfo);
    }
    interface  Model extends BaseModel {
        Request getModelPhoneLiveVideoInfo(Context context, String room_id);
    }
    abstract class Presenter extends BasePresenter<View,Model> {
              public abstract  void getPresenterPhoneLiveVideoInfo(String room_id);
      }

}
