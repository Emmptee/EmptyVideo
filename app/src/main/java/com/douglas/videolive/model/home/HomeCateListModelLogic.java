package com.douglas.videolive.model.home;


import android.content.Context;

import com.douglas.videolive.api.home.HomeApi;
import com.douglas.videolive.model.ParamsMapUtils;
import com.douglas.videolive.model.home.bean.HomeCateList;
import com.douglas.videolive.net.http.HttpUtils;
import com.douglas.videolive.net.transformer.DefaultTransformer;
import com.douglas.videolive.presenter.home.HomeCateListContract;

import java.util.List;

import rx.Observable;

/**
 * Created by shidongfang on 2017/12/8.
 */

public class HomeCateListModelLogic implements HomeCateListContract.Model{
    @Override
    public Observable<List<HomeCateList>> getHomeCateList(Context context) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getHomeCateList(ParamsMapUtils.getDefaultParams())
//               进行预处理
                .compose(new DefaultTransformer<List<HomeCateList>>());
    }
}
