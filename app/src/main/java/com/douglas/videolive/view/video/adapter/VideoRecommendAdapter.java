package com.douglas.videolive.view.video.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.douglas.videolive.R;
import com.douglas.videolive.model.logic.video.bean.VideoHotAuthorColumn;
import com.douglas.videolive.model.logic.video.bean.VideoHotColumn;
import com.douglas.videolive.model.logic.video.bean.VideoRecommendHotCate;
import com.douglas.videolive.ui.refreshview.recyclerview.BaseRecyclerAdapter;
import com.douglas.videolive.view.home.activity.HomeRecommendFaceScoreActivity;
import com.douglas.videolive.view.home.activity.HomeRecommendFaceScoreLiveVideoActivity;
import com.douglas.videolive.view.home.adapter.FullyGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shidongfang on 2018/1/24.
 */

public class VideoRecommendAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder> {
    //最热
    private List<VideoHotColumn> mVideoHotColumn;
    //作者
    private List<VideoHotAuthorColumn> mVideoHotAuthorColumn;
    //最热adapter
    private VideoRecommendHotColumnAdapter mHotColumnAdapter;
    //作者
    private VideoHotAuthorColumnAdapter mVideoHotAuthorColumnAdapter;
    //全部栏目
    private List<VideoRecommendHotCate> mVideoRecommendHotCate;

    private VideoRecommendAllColumnAdapter mAllColumnAdapter;

    private Context context;

    //最热栏目  制定type类型

    public static final int TYPE_1 = 0xff01;
    public static final int TYPE_2 = 0Xff02;
    public static final int TYPE_3 = 0xff03;


    public VideoRecommendAdapter(Context context) {
        this.context = context;
        mVideoHotColumn = new ArrayList<VideoHotColumn>();
        mVideoHotAuthorColumn = new ArrayList<VideoHotAuthorColumn>();
        mVideoRecommendHotCate = new ArrayList<VideoRecommendHotCate>();
        mVideoHotAuthorColumnAdapter = new VideoHotAuthorColumnAdapter(context);
    }


    /**
     * 最热
     *
     * @param videoHotColumn
     */
    public void getVideoHotColumn(List<VideoHotColumn> videoHotColumn) {
        this.mVideoHotColumn.clear();
        this.mVideoHotColumn.addAll(videoHotColumn);
        notifyDataSetChanged();
    }

    /**
     * 作者
     *
     * @param videoHotAuthorColumns
     */
    public void getFaceScoreColumn(List<VideoHotAuthorColumn> videoHotAuthorColumns) {
        this.mVideoHotAuthorColumn.clear();
        this.mVideoHotAuthorColumn.addAll(videoHotAuthorColumns);
        mVideoHotAuthorColumnAdapter.getFaceScoreColumn(videoHotAuthorColumns);
        notifyDataSetChanged();
    }

    /**
     * 全部栏目
     *
     * @param videoRecommendHotCates
     */
    public void getAllColumn(List<VideoRecommendHotCate> videoRecommendHotCates) {
        this.mVideoRecommendHotCate.clear();
        this.mVideoRecommendHotCate.addAll(videoRecommendHotCates);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(View view) {
        return new ColumnViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        switch (viewType) {
            case TYPE_1:
                return new ColumnViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_video_recommend, parent, false));
            case TYPE_2:
                return new ColumnViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_home_recommend, parent, false));
            case TYPE_3:
                return new ColumnViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_home_recommend, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, boolean isItem) {
        if (holder instanceof ColumnViewHolder && position == 0){
            bindColumnHolder((ColumnViewHolder) holder,position);
        }else if (holder instanceof ColumnViewHolder && position == mVideoRecommendHotCate.size() +1){
            bindFaceSoreColumnHolder((ColumnViewHolder) holder,position,isItem);
        }else {
            bindAllColumnHolder((ColumnViewHolder) holder,position);
        }
    }

    /**
     * 热门作者
     *
     * @param holder
     * @param position
     * @param isItem
     */
    private void bindFaceSoreColumnHolder(ColumnViewHolder holder, int position, boolean isItem) {
        holder.img_column_icon.setImageResource(R.mipmap.icon_reco_mobile);
        holder.tv_column_name.setText("热门作者");
        holder.rv_column_list.setLayoutManager(new FullyGridLayoutManager(holder.rv_column_list.getContext(),
                1,GridLayoutManager.VERTICAL,false));
        holder.rv_column_list.setAdapter(mVideoHotAuthorColumnAdapter);
        holder.rl_column_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomeRecommendFaceScoreActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title",holder.tv_column_name.getText().toString());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    /**
     * 全部栏目
     * @param holder
     * @param position
     */
    private void bindAllColumnHolder(ColumnViewHolder holder, int position) {
        holder.img_column_icon.setImageResource(R.mipmap.icon_column);
        holder.tv_column_name.setText(mVideoRecommendHotCate.get(position-1).getCate_name());
        holder.rv_column_list.setLayoutManager(new FullyGridLayoutManager(holder.rv_column_list.getContext(),
                2,GridLayoutManager.VERTICAL,false));
        mAllColumnAdapter = new VideoRecommendAllColumnAdapter(holder.rv_column_list.getContext(),
                mVideoRecommendHotCate.get(position-1).getVideo_list());
        holder.rv_column_list.setAdapter(mAllColumnAdapter);
        holder.rl_column_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomeRecommendFaceScoreLiveVideoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title",holder.tv_column_name.getText().toString());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    /**最热栏目
     * @param holder
     * @param position
     */
    private void bindColumnHolder(ColumnViewHolder holder, int position){
        holder.img_column_icon.setImageResource(R.mipmap.reco_game_txt_icon);
        holder.tv_column_name.setText("热门视频");
        holder.rv_column_list.setLayoutManager(new FullyGridLayoutManager(holder.rv_column_list.getContext(),
                2, GridLayoutManager.VERTICAL,false));
        mHotColumnAdapter = new VideoRecommendHotColumnAdapter(holder.rv_column_list.getContext(),mVideoHotColumn);
        holder.rv_column_list.setAdapter(mHotColumnAdapter);
    }


    @Override
    public int getAdapterItemViewType(int position) {
        if (position ==0){
            return TYPE_1;
        }else if(position == mVideoRecommendHotCate.size() +1){
            return TYPE_2;
        }
            return TYPE_3;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch(type){
                        case TYPE_1:
                        case TYPE_2:
                        case TYPE_3:
                            return gridLayoutManager.getSpanCount();
                        default:
                            return 1;
                    }
                }
            });
        }
    }

    @Override
    public int getAdapterItemCount() {
        return mVideoRecommendHotCate.size() + 2;
    }

    public class ColumnViewHolder extends RecyclerView.ViewHolder {
        //       栏目 Icon
        public ImageView img_column_icon;
        //        栏目 名称
        public TextView tv_column_name;
        //        加载更多
        public RelativeLayout rl_column_more;
        //        栏目列表
        public RecyclerView rv_column_list;

        public LinearLayout item_home_recommed_girdview;

        public ColumnViewHolder(View itemView) {
            super(itemView);
            img_column_icon = itemView.findViewById(R.id.img_column_icon);
            tv_column_name = itemView.findViewById(R.id.tv_column_name);
            rl_column_more = itemView.findViewById(R.id.rl_column_more);
            rv_column_list = itemView.findViewById(R.id.rv_column_list);
            item_home_recommed_girdview = itemView.findViewById(R.id.item_home_recommed_girdview);
        }
    }
}
