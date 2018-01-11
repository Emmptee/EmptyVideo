package com.douglas.videolive.view.live.activity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.douglas.videolive.R;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.base.SwipeBackActivity;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;

import butterknife.BindView;

import static com.tencent.smtt.sdk.WebSettings.*;

/**
 * Created by shidongfang on 2018/1/5.
 */

public class webViewActivity extends SwipeBackActivity{

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.progressbar_webview)
    ProgressBar mProgressBar;
    @BindView(R.id.web_main)
    WebView mWebView;
    @BindView(R.id.activity_web_view)
    LinearLayout activityWebView;

    IX5WebChromeClient.CustomViewCallback mCallback;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);
        Intent intent = getIntent();
        String url = intent.getStringExtra("web_url");
        String title = intent.getStringExtra("web_title");
        setTitle(title);
        initView();
        mWebView.loadUrl(url);
    }

    private void initView() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setAllowFileAccess(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportMultipleWindows(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);
        mWebView.setDrawingCacheEnabled(true);
        webSettings

    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected BaseView getView() {
        return null;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
