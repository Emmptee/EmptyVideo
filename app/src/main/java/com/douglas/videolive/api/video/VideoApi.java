package com.douglas.videolive.api.video;


import com.douglas.videolive.model.logic.video.bean.VideoCateList;
import com.douglas.videolive.model.logic.video.bean.VideoHotAuthorColumn;
import com.douglas.videolive.model.logic.video.bean.VideoHotColumn;
import com.douglas.videolive.model.logic.video.bean.VideoOtherColumnList;
import com.douglas.videolive.model.logic.video.bean.VideoReClassify;
import com.douglas.videolive.model.logic.video.bean.VideoRecommendHotCate;
import com.douglas.videolive.net.response.HttpResponse;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

import static com.douglas.videolive.api.NetWorkApi.getVideoCateList;
import static com.douglas.videolive.api.NetWorkApi.getVideoHotAutherColumn;
import static com.douglas.videolive.api.NetWorkApi.getVideoHotColumn;
import static com.douglas.videolive.api.NetWorkApi.getVideoOtherList;
import static com.douglas.videolive.api.NetWorkApi.getVideoReCateList;
import static com.douglas.videolive.api.NetWorkApi.getVideoRecommendHotCate;
/**
 * Created by Administrator on 2017/2/7 0007.
 */

public interface VideoApi {


    /**
     * 推荐---最热
     *
     * @return
     */
    @GET(getVideoHotColumn)
    Observable<HttpResponse<List<VideoHotColumn>>> getVideoHotColumn(@QueryMap Map<String, String> params) ;


    /**
     *    推荐---颜值
     * @return
     */
    @GET(getVideoHotAutherColumn)
    Observable<HttpResponse<List<VideoHotAuthorColumn>>> getVideoHotAuther(@QueryMap Map<String, String> params);

    /**
     *    推荐---热门 种类
     * @return
     */
    @GET(getVideoRecommendHotCate)
    Observable<HttpResponse<List<VideoRecommendHotCate>>> getVideoHotCate(@QueryMap Map<String, String> params);

    /**
     *    推荐---全部分类
     * @return
     */
    @GET(getVideoCateList)
    Observable<HttpResponse<List<VideoCateList>>> getVideoCateList(@QueryMap Map<String, String> params);

    /**
     *    视频---二级分类
     * @return
     */
    @GET(getVideoReCateList)
    Observable<HttpResponse<List<VideoReClassify>>> getVideoReCateList(@QueryMap Map<String, String> params);

    /**
     *    视频---二级列表
     * @return
    */
    @GET(getVideoOtherList)
    Observable<HttpResponse<List<VideoOtherColumnList>>> getVideoOtherColumnList(@QueryMap Map<String, String> params);
}
