package com.douglas.videolive.model.logic.video;

import android.content.Context;

import com.douglas.videolive.api.video.VideoApi;
import com.douglas.videolive.model.ParamsMapUtils;
import com.douglas.videolive.model.logic.video.bean.VideoReClassify;
import com.douglas.videolive.net.http.HttpUtils;
import com.douglas.videolive.net.transformer.DefaultTransformer;
import com.douglas.videolive.presenter.video.interfaces.VideoOtherCateContract;

import java.util.List;

import rx.Observable;


/**
 * Created by Administrator on 2017/2/9 0009.
 */

public class VideoOtherCateListLogic implements VideoOtherCateContract.Model{
    @Override
    public Observable<List<VideoReClassify>> getModelVideoAllCate(Context context , String cid) {

        return HttpUtils.getInstance(context)
                .getRetofitClinet()
                .setBaseUrl(" http://apiv2.douyucdn.cn")
                .builder(VideoApi.class)
                .getVideoReCateList(ParamsMapUtils.getVideoOtherTwoColumn(cid))

//               进行预处理
                .compose(new DefaultTransformer<List<VideoReClassify>>());
    }
}
