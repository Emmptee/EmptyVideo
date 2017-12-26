package com.douglas.videolive.view.common.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.douglas.videolive.R;
import com.douglas.videolive.base.BaseActivity;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.danmu.DanmuProcess;
import com.douglas.videolive.model.logic.bean.TempLiveVideoInfo;
import com.douglas.videolive.model.logic.common.CommonPhoneLiveVideoModelLogic;
import com.douglas.videolive.presenter.common.impl.CommonPhoneLiveVideoPresenterImp;
import com.douglas.videolive.presenter.common.interfaces.CommonPhoneLiveVideoContract;
import com.douglas.videolive.ui.DivergeView;
import com.douglas.videolive.ui.loadplay.LoadingView;
import com.douglas.videolive.view.common.fragment.MainDialogFragment;

import java.security.Provider;
import java.util.ArrayList;

import butterknife.BindView;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.utils.ScreenResolution;
import io.vov.vitamio.widget.VideoView;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * Created by shidongfang on 2017/12/25.
 */

public class PhoneLiveVideoActivity extends BaseActivity<CommonPhoneLiveVideoModelLogic, CommonPhoneLiveVideoPresenterImp>
        implements CommonPhoneLiveVideoContract.View, MediaPlayer.OnInfoListener,
        MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnErrorListener {

    @BindView(R.id.vm_videoview)
    VideoView vmVideoView;
    @BindView(R.id.im_logo)
    ImageView imLogo;
    @BindView(R.id.lv_playloading)
    LoadingView lvPlayloading;
    @BindView(R.id.fl_loading)
    FrameLayout flLoading;
    @BindView(R.id.iv_control_img)
    ImageView ivControlImg;
    @BindView(R.id.tv_control_name)
    TextView tvCotrolName;
    @BindView(R.id.tv_control)
    TextView tvControl;
    @BindView(R.id.control_center)
    RelativeLayout controlCenter;
    @BindView(R.id.tv_loading_buffer)
    TextView tvLKoadingBuffer;
    @BindView(R.id.danmakuView)
    DanmakuView danmakuView;
    @BindView(R.id.img_loading)
    ImageView imgLoading;
    @BindView(R.id.divergeView)
    DivergeView divergeView;

    private String room_id;
    private String imgUrl;
    private SVProgressHUD svProgressHUD;
    /*音量、亮度*/
    private int mScreenWidth = 0;
    private AudioManager mAudioManager;
    private int mMaxVolume;
    private int mShowVolume;
    private int mShwoLightness;
    private DanmuProcess mDanmuProcess;
    private ArrayList<Bitmap> mList;//点赞的动画
    private int mIndex = 0;
    private GestureDetector mGestureDetector;
    private GestureDetector.SimpleOnGestureListener mSimpleOnGestureListener;


    @Override
    protected int getLayoutId() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Vitamio.isInitialized(this);
        return R.layout.activity_phonelive_video;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        room_id = getIntent().getExtras().getString("room_id");
        imgUrl = getIntent().getExtras().getString("Img_Path");
        if (imgUrl != null) {
            imgLoading.setImageURI(Uri.parse(imgUrl));
        }
        vmVideoView.setKeepScreenOn(true);//保持屏幕常亮
        mPresenter.getPresenterPhoneLiveVideoInfo(room_id);
        svProgressHUD = new SVProgressHUD(this);
        //获取屏幕宽度
        Pair<Integer, Integer> scrennPair = ScreenResolution.getResolution(this);
        mScreenWidth = scrennPair.first;
        initVolumeWithLight();
        vmVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH, 0);
        initDanMu(room_id);
        initDrawView();
        show();
        new MainDialogFragment().show(getSupportFragmentManager(), "MainDialogFragment");
    }

    private void show() {
        vmVideoView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }

    /**
     * 点赞
     */
    public void initDrawView() {
        mList = new ArrayList<>();
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.prise_icon1, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.prise_icon2, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.prise_icon3, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.prise_icon4, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.prise_icon5, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.prise_icon6, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.prise_icon7, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.prise_icon8, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.prise_icon9, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.prise_icon10, null)).getBitmap());
        divergeView.post(new Runnable() {
            @Override
            public void run() {
                divergeView.setEndPoint(new PointF(divergeView.getMeasuredWidth() / 2, 0));
                divergeView.setDivergeViewProvider(new Provider());
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (divergeView != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mIndex == 9) {
                                    mIndex = 0;
                                }
                                if (divergeView != null) {
                                    divergeView.startDiverges(mIndex);
                                    mIndex++;
                                }
                            }
                        });
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
            }
        }).start();
    }

    /**
     * 贝塞尔曲线
     */
    public class Provider implements DivergeView.DivergeViewProvider {

        @Override
        public Bitmap getBitmap(Object obj) {
            return mList == null ? null : mList.get((Integer) obj);
        }
    }

    private void initDanMu(String room_id) {
        mDanmuProcess = new DanmuProcess(this, danmakuView, Integer.valueOf(room_id));
        mDanmuProcess.start();
    }

    /**
     * 初始化声音和亮度
     */
    private void initVolumeWithLight() {
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mShowVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mShwoLightness = getScreenBrightness();
    }

    /**
     * 获取当前屏幕的亮度值0~255
     */
    private int getScreenBrightness() {
        int screenBrightness = 255;
        try {
            Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenBrightness;
    }

    @Override
    protected void onEvent() {
        addTouchListener();
    }

    private void addTouchListener() {
        mSimpleOnGestureListener = new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        };
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector != null) {
            mGestureDetector.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
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
