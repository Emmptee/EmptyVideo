package com.douglas.videolive.view.video.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.douglas.videolive.model.logic.video.bean.VideoReClassify;
import com.douglas.videolive.view.video.fragment.VideoOtherTwoColumnFragment;

import java.util.List;

/**
 * Created by shidongfang on 2018/1/26.
 */

public class videoReClassifyListAdapter extends FragmentStatePagerAdapter{

    private List<VideoReClassify> mHomeCateLists;
    private String[] mTitle;
    private FragmentManager mFragmentManager;

    public videoReClassifyListAdapter(FragmentManager fm, List<VideoReClassify> homeCateLists,String[] title) {
        super(fm);
        this.mFragmentManager = fm;
        this.mHomeCateLists = homeCateLists;
        this.mTitle = title;
    }

    @Override
    public Fragment getItem(int position) {
        return VideoOtherTwoColumnFragment;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
