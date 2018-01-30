package com.douglas.videolive.view.video.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.douglas.videolive.R;
import com.douglas.videolive.model.logic.video.bean.VideoHotColumn;
import com.douglas.videolive.utils.CalculationUtils;
import com.douglas.videolive.view.live.activity.WebViewActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by shidongfang on 2018/1/24.
 */

public class VideoRecommendHotColumnAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<VideoHotColumn> videoHotColumn;
    private Context context;

    public VideoRecommendHotColumnAdapter(Context context, List<VideoHotColumn> videoHotColumn) {
        this.videoHotColumn =  videoHotColumn;
        this.context =context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotColumnHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_video_recommend_view,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HotColumnHolder){
            bindHotColumn((HotColumnHolder) holder,position);
        }
    }

    private void bindHotColumn(HotColumnHolder holder, int position) {
        holder.img_item_gridview.setImageURI(Uri.parse(videoHotColumn.get(position).getVideo_cover()));
        holder.tv_column_item_nickname.setText(videoHotColumn.get(position).getVideo_title());
        holder.tv_nickname.setText(videoHotColumn.get(position).getNickname());
        holder.tv_watchnum.setText(CalculationUtils.getOnLine(videoHotColumn.get(position).getView_num()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("web_url","https://v.douyu.com/show/"+
                        videoHotColumn.get(position).getHash_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoHotColumn.size();
    }

    public class HotColumnHolder extends BaseViewHolder{

        //        图片
        public SimpleDraweeView img_item_gridview;
        //        房间名称
        public TextView tv_column_item_nickname;

        public TextView tv_video_time;
        //        昵称
        public TextView tv_nickname;

        public TextView tv_watchnum;

        public HotColumnHolder(View view) {
            super(view);
            img_item_gridview = (SimpleDraweeView) view.findViewById(R.id.img_item_gridview);
            tv_column_item_nickname = (TextView) view.findViewById(R.id.tv_column_item_nickname);
            tv_video_time = (TextView) view.findViewById(R.id.tv_video_time);
            tv_nickname = (TextView) view.findViewById(R.id.tv_nickname);
            tv_watchnum = (TextView) view.findViewById(R.id.tv_watchnum);

        }
    }
}
