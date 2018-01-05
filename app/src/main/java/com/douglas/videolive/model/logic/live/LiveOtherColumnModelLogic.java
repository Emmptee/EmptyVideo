package com.douglas.videolive.model.logic.live;

import android.content.Context;


import com.douglas.videolive.api.live.LiveApi;
import com.douglas.videolive.model.ParamsMapUtils;
import com.douglas.videolive.net.http.HttpUtils;
import com.douglas.videolive.net.transformer.DefaultTransformer;
import com.douglas.videolive.presenter.live.interfaces.LiveOtherColumnContract;

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
public class LiveOtherColumnModelLogic implements LiveOtherColumnContract.Model{

    @Override
    public Observable<List<LiveOtherColumn>> getModelLiveOtherColumn(Context context) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(LiveApi.class)
                .getLiveOtherColumn(ParamsMapUtils.getDefaultParams())
//               进行预处理
                .compose(new DefaultTransformer<List<LiveOtherColumn>>());
    }
}
