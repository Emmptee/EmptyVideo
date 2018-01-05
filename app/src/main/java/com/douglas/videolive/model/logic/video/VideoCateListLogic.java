package com.douglas.videolive.model.logic.video;

import android.content.Context;


import com.douglas.videolive.api.video.VideoApi;
import com.douglas.videolive.model.ParamsMapUtils;
import com.douglas.videolive.model.logic.video.bean.VideoCateList;
import com.douglas.videolive.net.http.HttpUtils;
import com.douglas.videolive.net.transformer.DefaultTransformer;
import com.douglas.videolive.presenter.video.interfaces.VideoAllCateListContract;

import java.util.List;

import rx.Observable;


/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class VideoCateListLogic implements VideoAllCateListContract.Model {
    @Override
    public Observable<List<VideoCateList>> getModelVideoAllCate(Context context) {
        return HttpUtils.getInstance(context)
                .getRetofitClinet()
                .setBaseUrl(" http://apiv2.douyucdn.cn")
                .builder(VideoApi.class)
                .getVideoCateList(ParamsMapUtils.getDefaultParams())
//               进行预处理
                .compose(new DefaultTransformer<List<VideoCateList>>());

    }
}
