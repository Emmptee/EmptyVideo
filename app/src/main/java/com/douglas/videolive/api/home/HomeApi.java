package com.douglas.videolive.api.home;

import com.douglas.videolive.model.home.bean.HomeCarousel;
import com.douglas.videolive.model.home.bean.HomeCateList;
import com.douglas.videolive.model.home.bean.HomeColumnMoreAllList;
import com.douglas.videolive.model.home.bean.HomeColumnMoreOtherList;
import com.douglas.videolive.model.home.bean.HomeColumnMoreTwoCate;
import com.douglas.videolive.model.home.bean.HomeFaceScoreColumn;
import com.douglas.videolive.model.home.bean.HomeHotColumn;
import com.douglas.videolive.model.home.bean.HomeRecommendHotCate;
import com.douglas.videolive.net.response.HttpResponse;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

import static com.douglas.videolive.api.NetWorkApi.getCarousel;
import static com.douglas.videolive.api.NetWorkApi.getHomeCate;
import static com.douglas.videolive.api.NetWorkApi.getHomeCateList;
import static com.douglas.videolive.api.NetWorkApi.getHomeColumnMoreAllList;
import static com.douglas.videolive.api.NetWorkApi.getHomeColumnMoreCate;
import static com.douglas.videolive.api.NetWorkApi.getHomeColumnMoreOtherList;
import static com.douglas.videolive.api.NetWorkApi.getHomeFaceScoreColumn;
import static com.douglas.videolive.api.NetWorkApi.getHomeHotColumn;
import static com.douglas.videolive.api.NetWorkApi.getHomeRecommendHotCate;

/**
 * Created by shidongfang on 2017/12/8.
 */

public interface HomeApi {

    /**
     * 首页分类列表
     *
     * @return
     */
    @GET(getHomeCateList)
    Observable<HttpResponse<List<HomeCateList>>> getHomeCateList(@QueryMap Map<String, String> params);

    /**
     * 首页 列表详情页
     *
     * @return
     */
    @GET(getHomeCate)
    Observable<HttpResponse<List<HomeRecommendHotCate>>> getHomeCate(@QueryMap Map<String, String> params);

    /**
     * 首页   推荐轮播图
     *
     * @return
     */
    @GET(getCarousel)
    Observable<HttpResponse<List<HomeCarousel>>> getCarousel(@QueryMap Map<String, String> params);

    /**
     * 推荐---最热
     *
     * @return
     */
    @GET(getHomeHotColumn)
    Observable<HttpResponse<List<HomeHotColumn>>> getHotColumn(@QueryMap Map<String, String> params);

    /**
     * 推荐---颜值
     *
     * @return
     */
    @GET(getHomeFaceScoreColumn)
    Observable<HttpResponse<List<HomeFaceScoreColumn>>> getFaceScoreColumn(@QueryMap Map<String, String> params);

    /**
     * 推荐---热门 种类
     *
     * @return
     */
    @GET(getHomeRecommendHotCate)
    Observable<HttpResponse<List<HomeRecommendHotCate>>> getHotCate(@QueryMap Map<String, String> params);


    /**
     * 栏目 更多   --二级分类列表
     *
     * @return
     */
    @GET(getHomeColumnMoreCate)
    Observable<HttpResponse<List<HomeColumnMoreTwoCate>>> getHomeColumnMoreCate(@QueryMap Map<String, String> params);

    /**
     * 栏目 更多   --其他列表
     *
     * @return
     */
    @GET(getHomeColumnMoreOtherList)
    Observable<HttpResponse<List<HomeColumnMoreOtherList>>> getHomeColumnMoreOtherList(@QueryMap Map<String, String> params);

    /**
     * 栏目 更多   --全部列表
     *
     * @return
     */
    @GET(getHomeColumnMoreAllList + "{cate_id}")
    Observable<HttpResponse<List<HomeColumnMoreAllList>>> getHomeColumnMoreAllList(@Path("cate_id") String cate_id, @QueryMap Map<String, String> params);


}
