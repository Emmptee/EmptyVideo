package com.douglas.videolive.view.video.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.douglas.videolive.R;
import com.douglas.videolive.model.logic.video.bean.VideoOtherColumnList;
import com.douglas.videolive.ui.refreshview.recyclerview.BaseRecyclerAdapter;
import com.douglas.videolive.view.live.adapter.LiveOtherColumnListAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shidongfang on 2018/1/26.
 */

public class VideoOtherColumnListAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder> {

    private List<VideoOtherColumnList> mLiveList;
    private Context context;

    public VideoOtherColumnListAdapter( Context context) {
        this.mLiveList = new ArrayList<VideoOtherColumnList>();
        this.context = context;
    }

    public void getLiveOtherColumnList(List<VideoOtherColumnList> liveList){
        this.mLiveList.clear();
        this.mLiveList.addAll(liveList);
        notifyDataSetChanged();
    }
    public void getLiveOtherColumnLoadMore(List<VideoOtherColumnList> liveList){
        this.mLiveList.addAll(liveList);
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder getViewHolder(View view) {
        return new LiveOtherColumnListHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        return new LiveOtherColumnListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_other_view,parent,false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, boolean isItem) {
        if (holder instanceof LiveOtherColumnListHolder){
            bindLiveAll(holder,position);
        }
    }

    private void bindLiveAll(LiveOtherColumnListHolder holder, int position) {
        holder.img_item_gridview.setImageURI(Uri.parse(mLiveList.get(position).getVideo_cover()));

    }

    @Override
    public int getAdapterItemCount() {
        return 0;
    }

    private class LiveOtherColumnListHolder extends RecyclerView.ViewHolder {
        //        图片
        public SimpleDraweeView img_item_gridview;
        //        房间名称
        public TextView tv_column_item_nickname;
        //        在线人数
        public TextView tv_online_num;
        //        昵称
        public TextView tv_nickname;

        public LiveOtherColumnListHolder(View view) {
            super(view);
            img_item_gridview =  view.findViewById(R.id.img_item_gridview);
            tv_column_item_nickname = view.findViewById(R.id.tv_column_item_nickname);
            tv_online_num = view.findViewById(R.id.tv_online_num);
            tv_nickname = view.findViewById(R.id.tv_nickname);
        }
    }
}
