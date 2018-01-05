package com.douglas.videolive.api.live;


import com.douglas.videolive.model.logic.live.LiveOtherColumn;
import com.douglas.videolive.model.logic.live.bean.LiveAllList;
import com.douglas.videolive.model.logic.live.bean.LiveOtherList;
import com.douglas.videolive.model.logic.live.bean.LiveOtherTwoColumn;
import com.douglas.videolive.model.logic.live.bean.LiveSportsAllList;
import com.douglas.videolive.net.response.HttpResponse;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;
import static com.douglas.videolive.api.NetWorkApi.getLiveAllList;
import static com.douglas.videolive.api.NetWorkApi.getLiveOtherColumn;
import static com.douglas.videolive.api.NetWorkApi.getLiveOtherTwoColumn;
import static com.douglas.videolive.api.NetWorkApi.getLiveOtherTwoList;
import static com.douglas.videolive.api.NetWorkApi.getLiveSportsAllList;

/**
 **/
public interface LiveApi {
    /**
     *  直播其他栏目分类
     * @return
     */
    @GET(getLiveOtherColumn)
    Observable<HttpResponse<List<LiveOtherColumn>>> getLiveOtherColumn(@QueryMap Map<String, String> params);

    /**
     *  全部直播
     * @return
     */
    @GET(getLiveAllList)
    Observable<HttpResponse<List<LiveAllList>>> getLiveAllList(@QueryMap Map<String, String> params);

    /**
     *  直播其他栏目二级分类
     * @return
     */
    @GET(getLiveOtherTwoColumn)
    Observable<HttpResponse<List<LiveOtherTwoColumn>>> getLiveOtherTwoColumn(@QueryMap Map<String, String> params);
    /**
     *  直播其他列表页
     * @return
     */
    @GET(getLiveOtherTwoList+"{cate_id}")
    Observable<HttpResponse<List<LiveOtherList>>> getLiveOtherList(@Path("cate_id") String cate_id, @QueryMap Map<String, String> params);
    /**
     *  体育直播
     * @return
     */
    @GET(getLiveSportsAllList)
    Observable<HttpResponse<List<LiveSportsAllList>>> getLiveSportsAllList(@QueryMap Map<String, String> params);

}
