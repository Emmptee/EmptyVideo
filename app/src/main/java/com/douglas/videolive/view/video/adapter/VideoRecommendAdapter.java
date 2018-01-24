package com.douglas.videolive.view.video.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.douglas.videolive.model.logic.video.bean.VideoHotAuthorColumn;
import com.douglas.videolive.model.logic.video.bean.VideoHotColumn;
import com.douglas.videolive.ui.refreshview.recyclerview.BaseRecyclerAdapter;

import java.util.List;

/**
 * Created by shidongfang on 2018/1/24.
 */

public class VideoRecommendAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder>{
    //最热
    private List<VideoHotColumn> mVideoHotColumn;
    //作者
    private List<VideoHotAuthorColumn> mVideoHotAuthorColumn;
    //最热adapter
    private VideoRecommendHotColumnAdapter mHotColumnAdapter;

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
