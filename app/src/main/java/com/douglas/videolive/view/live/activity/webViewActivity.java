package com.douglas.videolive.view.live.activity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.douglas.videolive.R;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.base.SwipeBackActivity;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;

import butterknife.BindView;
import butterknife.OnClick;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;


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
        webSettings.setAppCacheMaxSize(Long.MAX_VALUE);
        webSettings.setAppCachePath(this.getDir("appcache",0).getPath());
        webSettings.setDatabasePath(this.getDir("databases",0).getPath());
        webSettings.setGeolocationDatabasePath(this.getDir("geolocation",0).getPath());
        webSettings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        initWebView();
    }

    private void initWebView() {
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return false;

            }
        });
        mWebView.setWebChromeClient(new WebChromeClient(){//全屏播放设置
            @Override
            public void onShowCustomView(View view, int i, IX5WebChromeClient.CustomViewCallback customViewCallback) {
                mCallback = customViewCallback;
            }

            @Override
            public void onHideCustomView() {
                if (mCallback != null) {
                    mCallback.onCustomViewHidden();
                    mCallback = null;
                }

                if (mWebView != null) {
                    ViewGroup viewGroup = (ViewGroup) mWebView.getParent();
                    viewGroup.removeView(mWebView);
                }
            }

            @Override
            public void onReceivedTitle(WebView webView, String s) {
                super.onReceivedTitle(webView, s);
                tvTitle.setText(s);
            }

            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                changeProgress(i);
            }
        });
    }

    private void changeProgress(int i) {
        if (i >= 0&& i<100){
            mProgressBar.setProgress(i);
            mProgressBar.setVisibility(View.VISIBLE);
        }else if (i== 100){
            mProgressBar.setProgress(100);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected BaseView getView() {
        return null;
    }
    @OnClick(R.id.img_back)
    public void back(){
        finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.destroy();
        }
        super.onDestroy();
    }
}
