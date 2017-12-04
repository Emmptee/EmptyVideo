package com.douglas.videolive.view.common.activity;

import android.os.Bundle;

import com.douglas.videolive.base.BaseActivity;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.logic.CommonPcLiveVideoModelLogic;
import com.douglas.videolive.model.logic.bean.TempLiveVideoInfo;
import com.douglas.videolive.presenter.common.impl.CommonPcLiveVideoPresenterImp;
import com.douglas.videolive.presenter.common.interfaces.CommonPcLiveVideoContract;

import io.vov.vitamio.MediaPlayer;

public class PcLiveVideoActivity extends BaseActivity<CommonPcLiveVideoModelLogic,CommonPcLiveVideoPresenterImp>
        implements CommonPcLiveVideoContract.View,MediaPlayer.OnBufferingUpdateListener{
    @Override
    protected int getLayoutId() {
        return 0;
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
    public void getViewPcLiveVideoInfo(TempLiveVideoInfo mLiveVideoInfo) {

    }

    /**
     * Called to update status in buffering a media stream. Buffering is storing
     * data in memory while caching on external storage.
     *
     * @param mp      the MediaPlayer the update pertains to
     * @param percent the percentage (0-100) of the buffer that has been filled thus
     */
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
