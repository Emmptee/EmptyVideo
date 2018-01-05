package com.douglas.videolive.presenter.video.interfaces;

import android.content.Context;


import com.douglas.videolive.base.BaseModel;
import com.douglas.videolive.base.BasePresenter;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.logic.video.bean.VideoHotAuthorColumn;
import com.douglas.videolive.model.logic.video.bean.VideoHotColumn;
import com.douglas.videolive.model.logic.video.bean.VideoRecommendHotCate;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2017/2/7 0007.
 */

public interface VideoRerecommendContract {
    interface View extends BaseView {
        //        最热栏目
        void getViewHotColumn(List<VideoHotColumn> mVideoHotColumn);

        //        颜值栏目
        void getViewHotAutherColumn(List<VideoHotAuthorColumn> videoHotAuthorColumns);

        //       热门栏目
        void getViewHotCate(List<VideoRecommendHotCate> videoRecommendHotCates);
    }
    interface Model extends BaseModel {
        Observable<List<VideoHotColumn>> getModelVideoHotColumn(Context context);

        Observable<List<VideoHotAuthorColumn>> getModelVideoHotAuthorColumn(Context context, int offset, int limit);

        Observable<List<VideoRecommendHotCate>> getModelVideoHotCate(Context context);
    }

    abstract class Presenter extends BasePresenter<View, Model> {

        //        最热栏目
        public abstract void getPresenterVideoHotColumn();

        public abstract void getPresenterVideoHotAutherColumn(int offset,int limit );

        public abstract void getPresenterVideoHotCate();

    }
}
