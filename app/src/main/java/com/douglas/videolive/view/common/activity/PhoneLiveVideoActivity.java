package com.douglas.videolive.view.common.activity;

import android.os.Bundle;
import android.view.WindowManager;

import com.douglas.videolive.R;
import com.douglas.videolive.base.BaseActivity;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.logic.bean.TempLiveVideoInfo;
import com.douglas.videolive.model.logic.common.CommonPhoneLiveVideoModelLogic;
import com.douglas.videolive.presenter.common.impl.CommonPhoneLiveVideoPresenterImp;
import com.douglas.videolive.presenter.common.interfaces.CommonPhoneLiveVideoContract;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;

/**
 * Created by shidongfang on 2017/12/25.
 */

public class PhoneLiveVideoActivity extends BaseActivity<CommonPhoneLiveVideoModelLogic,CommonPhoneLiveVideoPresenterImp>
    implements CommonPhoneLiveVideoContract.View,MediaPlayer.OnInfoListener,
        MediaPlayer.OnBufferingUpdateListener,MediaPlayer.OnErrorListener{
    @Override
    protected int getLayoutId() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Vitamio.isInitialized(this);
        return R.layout.activity_phonelive_video;
    }

    @Override
    protected void onInitView(Bundle bundle) {

    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected BaseView getView() {
        return null;
    }

    @Override
    public void showErrorWithStatus(String msg) {

    }

    @Override
    public void getViewPhoneLiveVideoInfo(TempLiveVideoInfo mLiveVideoInfo) {

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }
}
