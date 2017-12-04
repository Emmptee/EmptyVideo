package com.douglas.videolive.application;

import android.app.Application;
import android.content.Context;
import android.os.BatteryManager;
import android.os.Process;
import android.text.TextUtils;

import com.douglas.videolive.api.video.NetWorkApi;
import com.douglas.videolive.net.config.NetWorkConfiguration;
import com.douglas.videolive.net.http.HttpUtils;
import com.douglas.videolive.ui.pagestatemanager.PageManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.github.moduth.blockcanary.BlockCanary;
import com.socks.library.KLog;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by shidongfang on 2017/12/1.
 */

public class DYApplication extends Application {
    private static final String TAG = "DYApplication";
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        /*Buy收集*/
        String packageName = context.getPackageName();
        String processName = getProcessName(Process.myPid());
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(context, "3d003498d9", false);
        /*图片加载*/
        Fresco.initialize(context);
        /*UI卡顿检测*/
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
        /*搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核*/
        QbSdk.PreInitCallback callback =new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                KLog.e(TAG,"onCoreInitFinished ");
            }

            @Override
            public void onViewInitFinished(boolean b) {
                KLog.e(TAG,"onViewInitFinished is " + b);

            }
        };

        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                KLog.d(TAG,"onDownloadFinish is " + i);
            }

            @Override
            public void onInstallFinish(int i) {
                KLog.d(TAG,"onInstallFinish is " + i);
            }

            @Override
            public void onDownloadProgress(int i) {
                KLog.d(TAG,"onDownloadProgress is " + i);
            }
        });

        QbSdk.initX5Environment(getApplicationContext(),callback);
        //网络库初始化
        initOkHttpUtils();
        PageManager.initInApp(context);
        //内存检测
        initLeakCanary();

    }

    private static String getProcessName(int pid) {
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = bufferedReader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    private void initLeakCanary(){
        if (LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
    }
    private void initOkHttpUtils(){
        NetWorkConfiguration configuration = new NetWorkConfiguration(this)
                .baseUrl(NetWorkApi.baseUrl)
                .isCache(true)
                .isDiskCache(true)
                .isMemoryCache(true);
        HttpUtils.setConFiguration(configuration);
    }
    public static Context getContext(){
        return context;
    }

}

