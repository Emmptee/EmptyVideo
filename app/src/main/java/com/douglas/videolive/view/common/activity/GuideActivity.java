package com.douglas.videolive.view.common.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.douglas.videolive.R;
import com.douglas.videolive.view.common.adapter.GuideAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shidongfang on 2017/11/29.
 */

public class GuideActivity extends AppCompatActivity {
    Context context = this;

    @BindView(R.id.guide)
    ViewPager vp_guide;

    int[] guides = new int[]{
            R.mipmap.guide_bg1,
            R.mipmap.guide_bg2,
            R.mipmap.guide_bg3,
            R.mipmap.guide_bg4
    };
    private GuideAdapter guideAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        guideAdapter = new GuideAdapter(guides, context);
        vp_guide.setAdapter(guideAdapter);
    }
}
