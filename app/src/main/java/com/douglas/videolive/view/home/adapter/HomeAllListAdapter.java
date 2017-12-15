package com.douglas.videolive.view.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.douglas.videolive.model.home.bean.HomeCateList;

import java.util.List;

/**
 * Created by shidongfang on 2017/12/13.
 */

public class HomeAllListAdapter extends FragmentStatePagerAdapter {

    private List<HomeCateList> mHomeCateList;
    private String[] mTitle;
    private FragmentManager mFragmentManager;

    public HomeAllListAdapter(FragmentManager fm, List<HomeCateList> homeCateList, String[] title) {
        super(fm);
        this.mFragmentManager = fm;
        this.mHomeCateList = homeCateList;
        this.mTitle = title;
    }


    @Override
    public Fragment getItem(int position) {
        if (position != 0) {
            return RecommendHomeFragment
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }

    @Override
    public int getCount() {
        return mTitle.length;
    }
}
