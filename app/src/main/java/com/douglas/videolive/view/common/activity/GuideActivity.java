package com.douglas.videolive.view.common.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.douglas.videolive.R;
import com.douglas.videolive.view.common.adapter.GuigeAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends AppCompatActivity {
    private static final String TAG = "GuideActivity";
    Context context = this;

    @BindView(R.id.guide)
    ViewPager vp_guide;

    int[] guides = new int[]{
            R.mipmap.guide_bg1,
            R.mipmap.guide_bg2,
            R.mipmap.guide_bg3,
            R.mipmap.guide_bg4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        GuigeAdapter adapter = new GuigeAdapter(guides, context);
        vp_guide.setAdapter(adapter);

    }
}
