package com.douglas.videolive.model.logic.video;

import android.content.Context;


import com.douglas.videolive.api.video.VideoApi;
import com.douglas.videolive.model.ParamsMapUtils;
import com.douglas.videolive.model.logic.video.bean.VideoOtherColumnList;
import com.douglas.videolive.net.http.HttpUtils;
import com.douglas.videolive.net.transformer.DefaultTransformer;
import com.douglas.videolive.presenter.video.interfaces.VideoOtherTwoColumnContract;

import java.util.List;

import rx.Observable;

/**
 *  作者：gaoyin
 *  电话：18810474975
 *  邮箱：18810474975@163.com
 *  版本号：1.0
 *  类描述：
 *  备注消息：
 *  修改时间：2017/2/7 下午3:48
 **/
public class VideoTwoColumnModelLogic implements VideoOtherTwoColumnContract.Model{
    @Override
    public Observable<List<VideoOtherColumnList>> getModelVideoOtherTwoColumn(Context context, String cid1, String cid2, int offset, int limit, String action ) {
        return HttpUtils.getInstance(context)

                .setLoadDiskCache(true)
                .getRetofitClinet()
                .setBaseUrl("http://apiv2.douyucdn.cn")
                .builder(VideoApi.class)
                .getVideoOtherColumnList(ParamsMapUtils.getVideoOtherList(cid1,cid2,offset,limit,action))
                .compose(new DefaultTransformer<List<VideoOtherColumnList>>());
    }
}
