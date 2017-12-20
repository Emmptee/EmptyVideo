package com.douglas.videolive.view.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.douglas.videolive.model.home.bean.HomeFaceScoreColumn;
import com.douglas.videolive.model.home.bean.HomeHotColumn;
import com.douglas.videolive.model.home.bean.HomeRecommendHotCate;
import com.douglas.videolive.ui.refreshview.recyclerview.BaseRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by shidongfang on 2017/12/19.
 */

public class HomeRecommendAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder> {

    public static final int TYPE_1 = 0xff01;
    public static final int TYPE_2 = 0xff02;
    public static final int TYPE_3 = 0xff03;
    private final ArrayList<HomeRecommendHotCate> mHomeRecommendHotCate;
    private final HomeRecommendFaceScoreColumnAdapter mFaceScoreColumnAdapter;
    private final Context context;

    public HomeRecommendAdapter(Context context) {
        this.context = context;
        ArrayList<HomeHotColumn> mHomeHotColumn = new ArrayList<HomeHotColumn>();
        ArrayList<HomeFaceScoreColumn> mHomeFaceScoreColumn = new ArrayList<HomeFaceScoreColumn>();
        mHomeRecommendHotCate = new ArrayList<HomeRecommendHotCate>();
        mFaceScoreColumnAdapter = new HomeRecommendFaceScoreColumnAdapter(context);

    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(View view) {
        return null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, boolean isItem) {

    }

    @Override
    public int getAdapterItemCount() {
        return 0;
    }
}
