package com.douglas.videolive.view.video.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.douglas.videolive.model.logic.video.bean.VideoCateList;
import com.douglas.videolive.view.video.fragment.OtherVideoFragment;
import com.douglas.videolive.view.video.fragment.RecommendVideoFragment;

import java.util.List;

/**
 * Created by shidongfang on 2018/1/23.
 */

public class VideoAllListAdapter extends FragmentStatePagerAdapter {

    private FragmentManager mFragmentManager;
    private List<VideoCateList> mVideoCateLists;
    private String[] mTitle;

    public VideoAllListAdapter(FragmentManager fm, List<VideoCateList> videoCateLists, String[] title) {
        super(fm);
        this.mFragmentManager = fm;
        this.mVideoCateLists = videoCateLists;
        this.mTitle = title;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return RecommendVideoFragment.getInstance();
        }
        return OtherVideoFragment.getInstance(mVideoCateLists.get(position - 1),position);

    }

    @Override
    public int getCount() {
        return mTitle.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
