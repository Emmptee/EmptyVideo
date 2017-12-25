package com.douglas.videolive.model.logic.common;

import android.content.Context;
import android.net.Uri;

import com.douglas.videolive.presenter.common.interfaces.CommonPhoneLiveVideoContract;

import okhttp3.Request;

/**
 * Created by shidongfang on 2017/12/25.
 */

public class CommonPhoneLiveVideoModelLogic implements CommonPhoneLiveVideoContract.Model{
    @Override
    public Request getModelPhoneLiveVideoInfo(Context context, String room_id) {
        String string = "https://m.douyu.com/html5/live?roomId=" + room_id;
        Request requestPost = new Request.Builder()
                .url(string)
                .get()
                .build();
        return requestPost;

    }
}
