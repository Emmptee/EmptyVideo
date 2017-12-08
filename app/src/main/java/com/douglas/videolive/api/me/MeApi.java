package com.douglas.videolive.api.me;


import com.douglas.videolive.model.me.PersonInfoBean;
import com.douglas.videolive.net.response.HttpResponse;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

import static com.douglas.videolive.api.NetWorkApi.getPersonInfo;


/**
 * Created by Administrator on 2017/3/17 0017.
 */

public interface MeApi {
    /**
     * 推荐---最热
     *
     * @return
     */
    @GET(getPersonInfo)
    Observable<HttpResponse<PersonInfoBean>> getPersonInfos(@QueryMap Map<String, String> params) ;
}
