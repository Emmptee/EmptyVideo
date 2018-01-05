package com.douglas.videolive.api.common;



import com.douglas.videolive.model.logic.common.bean.LiveVideoInfo;
import com.douglas.videolive.net.response.HttpResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import static com.douglas.videolive.api.NetWorkApi.getLiveVideo;
/**

 **/
public interface CommonApi {
    /**
     *  直播播放页
     * @return
     */
    @GET(getLiveVideo+"{room_id}")
    Call<HttpResponse<LiveVideoInfo>> getLiveVideoInfo(@Path("room_id") String room_id, @QueryMap Map<String, String> params);

}
