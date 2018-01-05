package com.douglas.videolive.view.common.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.douglas.videolive.model.logic.common.bean.TempLiveVideoInfo;
import com.douglas.videolive.model.logic.common.CommonPhoneLiveVideoModelLogic;
import com.douglas.videolive.presenter.common.impl.CommonPhoneLiveVideoPresenterImp;
import com.douglas.videolive.presenter.common.interfaces.CommonPhoneLiveVideoContract;
import com.douglas.videolive.ui.DivergeView;
import com.douglas.videolive.ui.loadplay.LoadingView;
import com.douglas.videolive.view.common.fragment.MainDialogFragment;

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

public class PhoneLiveVideoActivity extends BaseActivity<CommonPhoneLiveVideoModelLogic,
        CommonPhoneLiveVideoPresenterImp> implements
        CommonPhoneLiveVideoContract.View, MediaPlayer.OnInfoListener,
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
    TextView tvControlName;
    @BindView(R.id.tv_control)
    TextView tvControl;
    @BindView(R.id.control_center)
    RelativeLayout controlCenter;
    @BindView(R.id.tv_loading_buffer)
    TextView tvLoadingBuffer;
    @BindView(R.id.danmakuView)
    DanmakuView danmakuView;
    @BindView(R.id.img_loading)
    ImageView imgLoading;
    @BindView(R.id.divergeView)
    DivergeView divergeView;

    private String room_id;
    private String imgUrl;
    private SVProgressHUD svProgressHUD;
    private DanmuProcess mDanmuProcess;
    private ArrayList<Bitmap> mList;//点赞的动画
    private int mIndex = 0;
    private TempLiveVideoInfo mVideoInfo;

    /*声音*/
    public final static int ADD_FLAG = 1;
    /*亮度*/
    public final static int SUB_FLAG = -1;

    /*音量、亮度*/
    private int mScreenWidth = 0;
    private AudioManager mAudioManager;
    private int mMaxVolume;//最大音量
    private int mShowVolume;//当前音量
    private int mShowLightness;//当前亮度

    private GestureDetector mGestureDetector;
    private GestureDetector.SimpleOnGestureListener mSimpleOnGestureListener;
    private boolean mIsFullScreen;


    public static final int HIDE_CONTROL_BAR = 0x02;//隐藏控制条
    public static final int HIDE_TIME = 5000;//隐藏控制条时间
    public static final int SHOW_CENTER_CONTROL = 0x03;//显示中间控制
    public static final int SHOW_CONTROL_TIME = 1000;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HIDE_CONTROL_BAR://隐藏top、bottom
                    break;
                case SHOW_CENTER_CONTROL://隐藏center控件
                    if (controlCenter != null) {
                        controlCenter.setVisibility(View.GONE);
                    }
                    break;
            }
        }
    };


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
        mShowLightness = getScreenBrightness();
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
        addTouchListener();//手势监听
        /*视频播放监听*/
        vmVideoView.setOnInfoListener(this);
        vmVideoView.setOnBufferingUpdateListener(this);
        vmVideoView.setOnErrorListener(this);
    }

    private void addTouchListener() {
        mSimpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (!mIsFullScreen) {//非全屏不进行手势操作
                    return false;
                }
                float x1 = e1.getX();
                float absDistanceX = Math.abs(distanceX);//distanceX < 0 从左到右
                float absDistanceY = Math.abs(distanceY);//distanceY < 0 从上到下

                //Y方向的距离比X方向的大，就是上下滑动
                if (absDistanceX < distanceY) {
                    if (distanceY > 0) {//向上滑动
                        if (x1 >= mScreenWidth * 0.65) {//右侧调节音量
                            changeVolume(ADD_FLAG);
                        } else {//调节亮度
                            changeLightness(ADD_FLAG);
                        }
                    } else {//向下滑动
                        if (x1 >= mScreenWidth * 0.65) {
                            changeVolume(SUB_FLAG);
                        } else {
                            changeLightness(SUB_FLAG);
                        }
                    }
                } else {//x方向的距离比Y方向的大

                }
                return false;
            }
            //双击事件，有的视频播放器支持双击暂停，在这实现

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                return super.onDoubleTap(e);
            }
            //单击事件

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return true;
            }
        };
        mGestureDetector = new GestureDetector(this, mSimpleOnGestureListener);
    }

    /**
     * 调节亮度
     *
     * @param addFlag
     */
    private void changeLightness(int addFlag) {
        mShowLightness += addFlag;
        if (mShowLightness > 255) {
            mShowLightness = 255;
        } else if (mShowLightness <= 0) {
            mShowLightness = 0;
        }
        tvControlName.setText("亮度");
        ivControlImg.setImageResource(R.drawable.img_light);
        tvControl.setText(mShowLightness * 100 / 255 + "%");
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.screenBrightness = mShowLightness / 255f;
        getWindow().setAttributes(lp);

        mHandler.removeMessages(SHOW_CENTER_CONTROL);
        controlCenter.setVisibility(View.VISIBLE);
        mHandler.sendEmptyMessageDelayed(SHOW_CENTER_CONTROL, SHOW_CONTROL_TIME);

    }

    /**
     * 调整音量
     *
     * @param addFlag
     */
    private void changeVolume(int addFlag) {
        mShowVolume += addFlag;
        if (mShowVolume > 100) {
            mShowVolume = 100;
        } else if (mShowVolume < 0) {
            mShowVolume = 0;
        }
        tvControlName.setText("音量");
        ivControlImg.setImageResource(R.drawable.img_volume);
        tvControl.setText(mShowVolume + "%");
        int tagVolume = mShowVolume * mMaxVolume / 100;//音量的绝对值
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, tagVolume, 0);
        mHandler.removeMessages(SHOW_CENTER_CONTROL);
        controlCenter.setVisibility(View.VISIBLE);
        mHandler.sendEmptyMessageDelayed(SHOW_CENTER_CONTROL, SHOW_CONTROL_TIME);
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
        return this;
    }

    @Override
    public void getViewPhoneLiveVideoInfo(TempLiveVideoInfo mLiveVideoInfo) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mVideoInfo = mLiveVideoInfo;
                getViewInfo(mLiveVideoInfo);
            }
        });
    }

    private void getViewInfo(TempLiveVideoInfo liveVideoInfo) {
        if (liveVideoInfo != null) {
            String url = liveVideoInfo.getData().getHls_url();
            Uri uri = Uri.parse(url);
            if (vmVideoView != null) {
                vmVideoView.setVideoURI(uri);
                vmVideoView.setBufferSize(1024 * 1024 * 20);
                vmVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
                vmVideoView.requestFocus();
                vmVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setPlaybackSpeed(1.0f);
                        flLoading.setVisibility(View.GONE);
                        mHandler.sendEmptyMessageDelayed(HIDE_CONTROL_BAR, HIDE_TIME);
                    }
                });
            }
        }
    }

    @Override
    public void showErrorWithStatus(String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                svProgressHUD.showErrorWithStatus("主播还在赶来的路上~~~");
            }
        });
    }

    /**
     * 正在缓冲
     *
     * @param mp      the MediaPlayer the update pertains to
     * @param percent the percentage (0-100) of the buffer that has been filled thus
     */
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        if (flLoading != null) {
            flLoading.setVisibility(View.VISIBLE);
        }
        if (vmVideoView != null) {
            if (vmVideoView.isPlaying())
                vmVideoView.pause();
            if (tvLoadingBuffer != null) {
                tvLoadingBuffer.setText("直播已缓冲" + percent + "%....");
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPresenter.getPresenterPhoneLiveVideoInfo(room_id);
        if (vmVideoView != null) {
            vmVideoView.start();
        }
        if (danmakuView != null && mDanmuProcess != null) {
            danmakuView.restart();
            mDanmuProcess.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (vmVideoView != null) {
            vmVideoView.pause();
        }
        if (danmakuView != null) {
            danmakuView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        if (vmVideoView != null) {
            vmVideoView.stopPlayback();//释放资源
        }
        if (divergeView != null) {
            divergeView.stop();//销毁点赞动画
            divergeView= null;
        }
        mDanmuProcess.finish();
        mDanmuProcess.close();
        if (danmakuView != null) {
            danmakuView.release();
            danmakuView.clear();
        }
        super.onDestroy();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        if (what==MediaPlayer.MEDIA_ERROR_UNKNOWN){
            svProgressHUD.showErrorWithStatus("主播还在赶来的路上---");
        }
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch(what){
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                if (vmVideoView.isPlaying()) {
                    vmVideoView.pause();
                }
                mHandler.removeMessages(HIDE_CONTROL_BAR);
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                flLoading.setVisibility(View.GONE);
                if (!vmVideoView.isPlaying())
                    vmVideoView.start();
                mHandler.sendEmptyMessageDelayed(HIDE_CONTROL_BAR,HIDE_TIME);
                break;
            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                break;
        }
        return true;
    }
}
