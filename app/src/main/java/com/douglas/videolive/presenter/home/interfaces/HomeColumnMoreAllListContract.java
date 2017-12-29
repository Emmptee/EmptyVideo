package com.douglas.videolive.presenter.home.interfaces;

import android.content.Context;

import com.douglas.videolive.base.BaseModel;
import com.douglas.videolive.base.BasePresenter;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.home.bean.HomeColumnMoreAllList;

import java.util.List;

import rx.Observable;


/**

 **/
public interface HomeColumnMoreAllListContract {
    interface View extends BaseView {
        void getViewHomeColumnAllList(List<HomeColumnMoreAllList> mHomeColumnMoreAllList);
        void getViewHomeColumnAllListLoadMore(List<HomeColumnMoreAllList> mHomeColumnMoreAllList);
    }

    interface Model extends BaseModel {

        Observable<List<HomeColumnMoreAllList>> getModelHomeColumnMoreAllList(Context context, String cate_id, int offset, int limit);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        /**
         * 获取全部列表
         */
        public abstract void getPresenterColumnMoreAllList(String cate_id, int offset, int limit);
        /**
         *  加载更多
         */
        public abstract  void getPresenterColumnMoreAllListLoadMore(String cate_id, int offset, int limit);

    }
}
