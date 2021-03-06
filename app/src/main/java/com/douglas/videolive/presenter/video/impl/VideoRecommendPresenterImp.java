package com.douglas.videolive.presenter.video.impl;



import com.douglas.videolive.model.logic.video.bean.VideoHotAuthorColumn;
import com.douglas.videolive.model.logic.video.bean.VideoHotColumn;
import com.douglas.videolive.model.logic.video.bean.VideoRecommendHotCate;
import com.douglas.videolive.net.callback.RxSubscriber;
import com.douglas.videolive.net.exception.ResponeThrowable;
import com.douglas.videolive.presenter.video.interfaces.VideoRerecommendContract;

import java.util.List;

/**
 * Created by Administrator on 2017/2/7 0007.
 */

public class VideoRecommendPresenterImp extends VideoRerecommendContract.Presenter{
    @Override
    public void getPresenterVideoHotColumn() {
        addSubscribe(mModel.getModelVideoHotColumn(mContext).subscribe(new RxSubscriber<List<VideoHotColumn>>() {
            @Override
            public void onSuccess(List<VideoHotColumn> mVideoHotColumn) {
                mView.getViewHotColumn(mVideoHotColumn);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }

    @Override
    public void getPresenterVideoHotAutherColumn(int offset, int limit) {
        addSubscribe(mModel.getModelVideoHotAuthorColumn(mContext, offset,limit).subscribe(new RxSubscriber<List<VideoHotAuthorColumn>>() {
            @Override
            public void onSuccess(List<VideoHotAuthorColumn> videoHotAuthorColumns) {
                mView.getViewHotAutherColumn(videoHotAuthorColumns);
            }

            @Override
            protected void onError(ResponeThrowable ex) {

                mView.showErrorWithStatus(ex.message);
            }

        }));

    }
    @Override
    public void getPresenterVideoHotCate() {
        addSubscribe(mModel.getModelVideoHotCate(mContext).subscribe(new RxSubscriber<List<VideoRecommendHotCate>>() {
            @Override
            public void onSuccess(List<VideoRecommendHotCate> videoRecommendHotCates) {
                mView.getViewHotCate(videoRecommendHotCates);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));

    }
}
