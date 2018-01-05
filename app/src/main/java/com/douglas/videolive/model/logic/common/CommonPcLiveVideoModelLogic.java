package com.douglas.videolive.model.logic.common;

import android.content.Context;

import com.douglas.videolive.base.BaseModel;
import com.douglas.videolive.presenter.common.interfaces.CommonPcLiveVideoContract;

import okhttp3.Request;

/**
 * Created by SDF on 2017/12/4.
 */

public class CommonPcLiveVideoModelLogic implements CommonPcLiveVideoContract.Model, BaseModel {
    @Override
    public Request getModelPcLiveVideoInfo(Context context, String room_id) {
        return null;

        //TODO 房间加密
    }
}
