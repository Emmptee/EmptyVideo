package com.douglas.videolive.model.logic.home;

import android.content.Context;

import com.douglas.videolive.api.home.HomeApi;
import com.douglas.videolive.model.ParamsMapUtils;
import com.douglas.videolive.model.home.bean.HomeColumnMoreTwoCate;
import com.douglas.videolive.net.http.HttpUtils;
import com.douglas.videolive.net.transformer.DefaultTransformer;
import com.douglas.videolive.presenter.home.interfaces.HomeColumnMoreListContract;

import java.util.List;

import rx.Observable;

/**
 * Created by shidongfang on 2017/12/29.
 */

public class HomeColumnMoreModeLogic implements HomeColumnMoreListContract.Model{
    @Override
    public Observable<List<HomeColumnMoreTwoCate>> getModelHomeColumnMoreTwoCate(Context context, String cate_id) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getHomeColumnMoreCate(ParamsMapUtils.getColumnMoreCate(cate_id))
//               进行预处理
                .compose(new DefaultTransformer<List<HomeColumnMoreTwoCate>>());
    }
}
