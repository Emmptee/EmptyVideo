package com.douglas.videolive.presenter.video.impl;




import com.douglas.videolive.model.logic.video.bean.VideoCateList;
import com.douglas.videolive.net.callback.RxSubscriber;
import com.douglas.videolive.net.exception.ResponeThrowable;
import com.douglas.videolive.presenter.video.interfaces.VideoAllCateListContract;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class VideoCateListPresenterImpl extends VideoAllCateListContract.Presenter {

    @Override
    public void getPresenterVideoCatelist() {
        addSubscribe(mModel.getModelVideoAllCate(mContext).subscribe(new RxSubscriber<List<VideoCateList>>() {
            @Override
            public void onSuccess(List<VideoCateList> mVideoCateList) {
                mView.getViewVideoAllCate(mVideoCateList);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
