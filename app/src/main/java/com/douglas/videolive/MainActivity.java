package com.douglas.videolive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String TAG_PAGE_HOME = "首页";
    private static final String TAG_PAGE_LIVE = "直播";
    private static final String TAG_PAGE_VIDEO = "视频";
    private static final String TAG_PAGE_FOLLOW = "关注";
    private static final String TAG_PAGE_USER = "我的";

    protected Unbinder ubinder;
    private long exitTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView viewById = (TextView) findViewById(R.id.all);
    }
}
