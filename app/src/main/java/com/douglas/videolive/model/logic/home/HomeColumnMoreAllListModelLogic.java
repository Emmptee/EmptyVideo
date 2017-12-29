package com.douglas.videolive.model.logic.home;

import android.content.Context;

import com.douglas.videolive.api.home.HomeApi;
import com.douglas.videolive.model.ParamsMapUtils;
import com.douglas.videolive.model.home.bean.HomeColumnMoreAllList;
import com.douglas.videolive.net.http.HttpUtils;
import com.douglas.videolive.net.transformer.DefaultTransformer;
import com.douglas.videolive.presenter.home.interfaces.HomeColumnMoreAllListContract;

import java.util.List;

import rx.Observable;


/**

 **/
public class HomeColumnMoreAllListModelLogic implements HomeColumnMoreAllListContract.Model {

    /**
     *  全部直播列表
     * @param context
     * @param cate_id
     * @return
     */
    @Override
    public Observable<List<HomeColumnMoreAllList>> getModelHomeColumnMoreAllList(Context context, String cate_id, int offset, int limit) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getHomeColumnMoreAllList(cate_id, ParamsMapUtils.getHomeFaceScoreColumn(offset,limit))
//               进行预处理
                .compose(new DefaultTransformer<List<HomeColumnMoreAllList>>());
    }
}
