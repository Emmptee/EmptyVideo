package com.douglas.videolive.model.logic.live;

import android.content.Context;

import com.douglas.videolive.api.live.LiveApi;
import com.douglas.videolive.model.ParamsMapUtils;
import com.douglas.videolive.model.logic.live.bean.LiveOtherTwoColumn;
import com.douglas.videolive.net.http.HttpUtils;
import com.douglas.videolive.net.transformer.DefaultTransformer;
import com.douglas.videolive.presenter.live.interfaces.LiveOtherTwoColumnContract;

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
public class LiveOtherTwoColumnModelLogic implements LiveOtherTwoColumnContract.Model{

    @Override
    public Observable<List<LiveOtherTwoColumn>> getModelLiveOtherTwoColumn(Context context, String mCloumnName) {
        return  HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(LiveApi.class)
                .getLiveOtherTwoColumn(ParamsMapUtils.getLiveOtherTwoColumn(mCloumnName))
//               进行预处理
                .compose(new DefaultTransformer<List<LiveOtherTwoColumn>>());
    }
}
