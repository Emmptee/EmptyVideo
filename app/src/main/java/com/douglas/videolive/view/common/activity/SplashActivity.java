package com.douglas.videolive.view.common.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.douglas.videolive.R;
import com.douglas.videolive.utils.SharedPreferenceUtils;


/**
 * Created by shidongfang on 2017/12/5.
 */

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    Intent intent;
    private boolean isFirst;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
//        这步取值操作不能放在run中，否则会执行两次导致页面跳转出错
        isFirst = SharedPreferenceUtils.getBooleanData("isFirst", true);
        handler = new Handler();
        handler.postDelayed(() -> {
            if (isFinishing()) {
                return;
            }
            if (isFirst) {
                intent = new Intent(SplashActivity.this, GuideActivity.class);
                SharedPreferenceUtils.setBooleanData("isFirst", false);

            } else {
                intent = new Intent(SplashActivity.this, MainActivity.class);
            }
            startActivity(intent);
            finish();
        }, 3000);

    }
}
