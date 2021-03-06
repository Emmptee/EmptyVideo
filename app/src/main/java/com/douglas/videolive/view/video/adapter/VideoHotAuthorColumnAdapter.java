package com.douglas.videolive.view.video.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.douglas.videolive.R;
import com.douglas.videolive.model.logic.video.bean.VideoHotAuthorColumn;
import com.douglas.videolive.ui.refreshview.recyclerview.BaseRecyclerAdapter;
import com.douglas.videolive.utils.CalculationUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shidongfang on 2018/1/25.
 */

public class VideoHotAuthorColumnAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder> {

    private List<VideoHotAuthorColumn> mVideoHotAuthorColumn;
    private Context context;

    public VideoHotAuthorColumnAdapter(Context context) {
        this.context = context;
        this.mVideoHotAuthorColumn = new ArrayList<VideoHotAuthorColumn>();
    }

    public void getFaceScoreColumn(List<VideoHotAuthorColumn> videoHotAuthorColumn) {
        this.mVideoHotAuthorColumn.clear();
        this.mVideoHotAuthorColumn.addAll(videoHotAuthorColumn);
        notifyDataSetChanged();
    }

    public void getFaceScoreColumnLoadMore(List<VideoHotAuthorColumn> videoHotAuthorColumn) {
//        this.mVideoHotAuthorColumn.clear();
        this.mVideoHotAuthorColumn.addAll(videoHotAuthorColumn);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(View view) {
        return new FaceScoreColumnHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        return new FaceScoreColumnHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_recommend_author, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, boolean isItem) {
        if (holder instanceof FaceScoreColumnHolder) {
            bindFaceScoreHolder((FaceScoreColumnHolder) holder, position);
        }
    }

    @Override
    public int getAdapterItemCount() {
        return mVideoHotAuthorColumn.size();
    }

    private void bindFaceScoreHolder(FaceScoreColumnHolder holder, int position) {
        holder.img_item_photo.setImageURI(Uri.parse(mVideoHotAuthorColumn.get(position).getAuthor_avatar()));
        holder.tv_nickname.setText(mVideoHotAuthorColumn.get(position).getNickname());
        holder.tv_sub_num.setText(mVideoHotAuthorColumn.get(position).getFollow_num());
        holder.tv_video_num.setText(CalculationUtils.getOnLine(mVideoHotAuthorColumn.get(position).getSubmit_num()));

    }

    public class FaceScoreColumnHolder extends BaseViewHolder {

        public SimpleDraweeView img_item_photo;
        public TextView tv_nickname;
        public TextView tv_video_num;
        public TextView tv_sub_num;

        public FaceScoreColumnHolder(View view) {
            super(view);
            img_item_photo = view.findViewById(R.id.img_item_photo);
            tv_nickname = view.findViewById(R.id.tv_nickname);
            tv_video_num = view.findViewById(R.id.tv_video_num);
            tv_sub_num = view.findViewById(R.id.tv_sub_num);
        }
    }


}
