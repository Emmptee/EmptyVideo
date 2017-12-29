package com.douglas.videolive.view.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.douglas.videolive.R;
import com.douglas.videolive.model.home.bean.HomeFaceScoreColumn;
import com.douglas.videolive.model.home.bean.HomeHotColumn;
import com.douglas.videolive.model.home.bean.HomeRecommendHotCate;
import com.douglas.videolive.ui.refreshview.recyclerview.BaseRecyclerAdapter;
import com.douglas.videolive.view.home.activity.HomeColumnMoreListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shidongfang on 2017/12/19.
 */

public class HomeRecommendAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder> {

    public static final int TYPE_1 = 0xff01;
    public static final int TYPE_2 = 0xff02;
    public static final int TYPE_3 = 0xff03;
    private HomeRecommendHotColumnAdapter mHotColumnAdapter;
    private final ArrayList<HomeRecommendHotCate> mHomeRecommendHotCate;
    private final HomeRecommendFaceScoreColumnAdapter mFaceScoreColumnAdapter;
    private final Context context;
    private final ArrayList<HomeFaceScoreColumn> mHomeFaceScoreColumn;
    private final ArrayList<HomeHotColumn> mHomeHotColumn;
    private HomeRecommendAllColumnAdapter mAllColumnAdapter;

    public HomeRecommendAdapter(Context context) {
        this.context = context;
        mHomeHotColumn = new ArrayList<HomeHotColumn>();
        mHomeFaceScoreColumn = new ArrayList<HomeFaceScoreColumn>();
        mHomeRecommendHotCate = new ArrayList<HomeRecommendHotCate>();
        mFaceScoreColumnAdapter = new HomeRecommendFaceScoreColumnAdapter(context);

    }


    /**
     * 最热栏目
     *
     * @param homeHotColumn
     */
    public void getHomeHotColumn(List<HomeHotColumn> homeHotColumn) {
        this.mHomeHotColumn.clear();
        this.mHomeHotColumn.addAll(mHomeHotColumn);
        notifyDataSetChanged();

    }

    /**
     * 颜值
     *
     * @param homeFaceScoreColumn
     */
    public void getFaceScoreColumn(List<HomeFaceScoreColumn> homeFaceScoreColumn) {
        this.mHomeFaceScoreColumn.clear();
        this.mHomeFaceScoreColumn.addAll(mHomeFaceScoreColumn);
        notifyDataSetChanged();
    }

    /**
     * 全部栏目
     *
     * @param homeRecommendHotCates
     */
    public void getAllColumn(List<HomeRecommendHotCate> homeRecommendHotCates) {
        this.mHomeRecommendHotCate.clear();
        this.mHomeRecommendHotCate.addAll(homeRecommendHotCates);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder getViewHolder(View view) {
        return new ColumnViewHoler(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        switch (viewType) {
            case TYPE_1:
            case TYPE_2:
            case TYPE_3:
                return new ColumnViewHoler(LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.item_home_recommend, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, boolean isItem) {
        if (holder instanceof ColumnViewHoler && position == 0) {
            bindColumnHolder((ColumnViewHoler) holder,position);
        }else if (holder instanceof ColumnViewHoler && position == 1){
            bindFaceScoreColumnHolder((ColumnViewHoler) holder,position,isItem);
        }else {
            bindAllColumnHolder((ColumnViewHoler) holder,position);
        }
    }

    private void bindColumnHolder(ColumnViewHoler holder,int position) {
        holder.img_column_icon.setImageResource(R.mipmap.reco_game_txt_icon);
        holder.tv_column_name.setText("HOT");
        holder.rv_column_list.setLayoutManager(new FullyGridLayoutManager(
                holder.rv_column_list.getContext(),2, GridLayoutManager.VERTICAL,false
        ));
        mHotColumnAdapter = new HomeRecommendHotColumnAdapter(
                holder.rv_column_list.getContext(),mHomeHotColumn);
        holder.rv_column_list.setAdapter(mHotColumnAdapter);
    }
    /**
     * 全部栏目
     *
     * @param holder
     * @param position
     */
    private void bindAllColumnHolder(ColumnViewHoler holder, int position) {
        holder.img_column_icon.setImageResource(R.mipmap.icon_column);
        holder.tv_column_name.setText(mHomeRecommendHotCate.get(position - 2).getTag_name());
        holder.rv_column_list.setLayoutManager(new FullyGridLayoutManager(holder.rv_column_list.getContext(), 2, GridLayoutManager.VERTICAL, false));
        mAllColumnAdapter = new HomeRecommendAllColumnAdapter(holder.rv_column_list.getContext(), mHomeRecommendHotCate.get(position - 2).getRoom_list());
        holder.rv_column_list.setAdapter(mAllColumnAdapter);
        holder.rl_column_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO  HomeColumnMoreListActivity
                Intent intent = new Intent(context, HomeColumnMoreListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", holder.tv_column_name.getText().toString());
                bundle.putString("cate_id", mHomeRecommendHotCate.get(position - 2).getTag_id());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }
    /**
     * 颜值 栏目
     *
     * @param holder
     * @param position
     * @param isItem
     */
    private void bindFaceScoreColumnHolder(ColumnViewHoler holder, int position, boolean isItem) {

        holder.img_column_icon.setImageResource(R.mipmap.icon_reco_mobile);
        holder.tv_column_name.setText("颜值");
        holder.rv_column_list.setLayoutManager(new FullyGridLayoutManager(holder.rv_column_list.getContext(), 2, GridLayoutManager.VERTICAL, false));
        holder.rv_column_list.setAdapter(mFaceScoreColumnAdapter);
        holder.rl_column_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO HomeColumnMoreListActivity
//                Intent intent = new Intent(context, HomeRecommendFaceScoreActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("title", holder.tv_column_name.getText().toString());
//                intent.putExtras(bundle);
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getAdapterItemViewType(int position) {
        if (position == 0){
            return TYPE_1;
        }else if (position == 1){
            return TYPE_2;
        }
        return TYPE_3;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager){
            final GridLayoutManager gridManager = (GridLayoutManager) manager;
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch(type){
                        case TYPE_1:
                        case TYPE_2:
                        case TYPE_3:
                            return gridManager.getSpanCount();
                        default:
                            return  1;
                    }
                }
            });
        }
    }

    @Override
    public int getAdapterItemCount() {
        return mHomeRecommendHotCate.size() + 2;
    }

    public class ColumnViewHoler extends RecyclerView.ViewHolder {

        public ImageView img_column_icon;//栏目图标
        public TextView tv_column_name;//名称
        public RelativeLayout rl_column_more;//加载更多
        public RecyclerView rv_column_list;//栏目列表
        public LinearLayout item_home_recommed_girdview;

        public ColumnViewHoler(View itemView) {
            super(itemView);
            img_column_icon = itemView.findViewById(R.id.img_column_icon);
            tv_column_name = itemView.findViewById(R.id.tv_column_name);
            rl_column_more = itemView.findViewById(R.id.rl_column_more);
            rv_column_list = itemView.findViewById(R.id.rv_column_list);
            item_home_recommed_girdview = itemView.findViewById(R.id.item_home_recommed_girdview);
        }
    }
}
