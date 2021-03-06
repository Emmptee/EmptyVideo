package com.douglas.videolive.presenter.video.impl;


import android.util.Log;

import com.douglas.videolive.model.logic.video.bean.VideoStramInfo;
import com.douglas.videolive.presenter.video.interfaces.VideoPhoneVideoInfoContract;
import com.douglas.videolive.utils.L;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 作者：gaoyin
 * 电话：18810474975
 * 邮箱：18810474975@163.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 修改时间：2017/2/24 下午3:27
 **/
public class VideoPhoneVideoInfoPresenterImp extends VideoPhoneVideoInfoContract.Presenter {
    @Override
    public void getPresenterPhoneLiveVideoInfo(String room_id) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        client.newCall(mModel.getModelPhoneLiveVideoInfo(mContext, room_id)).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.e("error", e.getMessage() + "---");
                L.e("错误信息:" + e.getMessage());
                mView.showErrorWithStatus(e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                Log.e("onResponse", response.body().string());
                String json = response.body().string().toString();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    if (jsonObject.getInt("error") == 0) {
                        Gson gson = new Gson();
                        VideoStramInfo mLiveVideoInfo = gson.fromJson(json, VideoStramInfo.class);
                        mView.getViewPhoneLiveVideoInfo(mLiveVideoInfo);
                    } else {
                        mView.showErrorWithStatus("获取数据失败!");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
