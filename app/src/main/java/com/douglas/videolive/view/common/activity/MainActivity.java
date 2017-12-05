package com.douglas.videolive.view.common.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.R;
import com.douglas.videolive.ui.NavigateTabBar;
import com.douglas.videolive.view.follow.FollowFragment;
import com.douglas.videolive.view.home.fragment.HomeFragment;
import com.douglas.videolive.view.live.fragment.LiveFragment;
import com.douglas.videolive.view.user.UserFragment;
import com.douglas.videolive.view.video.fragment.VideoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements BaseView{
    private static final String TAG = "MainActivity";
    private static final String TAG_PAGE_HOME = "首页";
    private static final String TAG_PAGE_LIVE = "直播";
    private static final String TAG_PAGE_VIDEO = "视频";
    private static final String TAG_PAGE_FOLLOW = "关注";
    private static final String TAG_PAGE_USER = "我的";

    private long exitTime = 0;
    protected Unbinder unbinder;

    @BindView(R.id.mainTabBar)
    NavigateTabBar mNavigateTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
//        mNavigateTabBar.onRestoreInstanceState(savedInstanceState);
        mNavigateTabBar.addTab(HomeFragment.class, new NavigateTabBar.TabParam(R.mipmap.home_pressed,
                R.mipmap.home_selected, TAG_PAGE_HOME));
        mNavigateTabBar.addTab(LiveFragment.class, new NavigateTabBar.TabParam(R.mipmap.live_pressed,
                R.mipmap.live_selected, TAG_PAGE_LIVE));
        mNavigateTabBar.addTab(VideoFragment.class, new NavigateTabBar.TabParam(R.mipmap.video,
                R.mipmap.video_selected, TAG_PAGE_VIDEO));
        mNavigateTabBar.addTab(FollowFragment.class, new NavigateTabBar.TabParam(R.mipmap.follow_pressed,
                R.mipmap.follow_selected, TAG_PAGE_FOLLOW));
        mNavigateTabBar.addTab(UserFragment.class, new NavigateTabBar.TabParam(R.mipmap.user_pressed,
                R.mipmap.user_selected, TAG_PAGE_USER));
        mNavigateTabBar.setTabSelectListener(new NavigateTabBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(NavigateTabBar.ViewHolder holder) {
                switch (holder.tag.toString()) {
                    case TAG_PAGE_HOME:
                        mNavigateTabBar.showFragment(holder);
                        break;
                    case TAG_PAGE_LIVE:
                        mNavigateTabBar.showFragment(holder);
                        break;
                    case TAG_PAGE_VIDEO:
                        mNavigateTabBar.showFragment(holder);
                        break;
                    case TAG_PAGE_FOLLOW:
                        mNavigateTabBar.showFragment(holder);
                        break;
                    case TAG_PAGE_USER:
                        mNavigateTabBar.showFragment(holder);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * 退出需要点击两次返回键
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再来一次", Toast.LENGTH_LONG);
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mNavigateTabBar.onSaveInstanceState(outState);
    }

    @Override
    public void showErrorWithStatus(String msg) {

    }
}
