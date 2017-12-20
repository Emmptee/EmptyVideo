package com.douglas.videolive.view.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.douglas.videolive.R;
import com.douglas.videolive.model.home.bean.HomeFaceScoreColumn;
import com.douglas.videolive.ui.refreshview.recyclerview.BaseRecyclerAdapter;
import com.douglas.videolive.utils.CalculationUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shidongfang on 2017/12/19.
 */

class HomeRecommendFaceScoreColumnAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder> {

    private List<HomeFaceScoreColumn> mHomeFaceScoreColumn;
    private Context mContext;

    public HomeRecommendFaceScoreColumnAdapter(Context context) {
        this.mContext = context;
        this.mHomeFaceScoreColumn = new ArrayList<HomeFaceScoreColumn>();
    }

    public void getFaceScroreColumn(List<HomeFaceScoreColumn> homeFaceScoreColumns) {
        this.mHomeFaceScoreColumn.clear();
        this.mHomeFaceScoreColumn.addAll(homeFaceScoreColumns);
        notifyDataSetChanged();
    }

    public void getFaceScoreColumnLoadMore(List<HomeFaceScoreColumn> homeFaceScoreColumns) {
        this.mHomeFaceScoreColumn.addAll(homeFaceScoreColumns);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder getViewHolder(View view) {
        return new FaceScoreColumnHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        return new FaceScoreColumnHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_home_recommend_facescore,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, boolean isItem) {
        if (holder instanceof FaceScoreColumnHolder){
            bindFaceScoreHolder((FaceScoreColumnHolder) holder,position);
        }
    }

    private void bindFaceScoreHolder(FaceScoreColumnHolder holder,int position){
        holder.img_item_gridview.setImageURI(Uri.parse(mHomeFaceScoreColumn.get(position).getVertical_src()));
        holder.tv_column_item_nickname.setText(mHomeFaceScoreColumn.get(position).getNickname());
        holder.tv_facescore_city.setText(mHomeFaceScoreColumn.get(position).getAnchor_city());
        holder.tv_online_num.setText(CalculationUtils.getOnLine(mHomeFaceScoreColumn.get(position).getOnline()));
        holder.img_item_gridview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 跳转PhoneLiveVideo
//                new Intent(mContext,phone)
            }
        });
    }

    @Override
    public int getAdapterItemCount() {
        return 0;
    }

    public class FaceScoreColumnHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView img_item_gridview;
        private final TextView tv_column_item_nickname;
        private final TextView tv_online_num;
        private final TextView tv_facescore_city;

        public FaceScoreColumnHolder(View itemView) {
            super(itemView);
            img_item_gridview = itemView.findViewById(R.id.img_item_gridview);
            tv_column_item_nickname = itemView.findViewById(R.id.tv_column_item_nickname);
            tv_online_num = itemView.findViewById(R.id.tv_online_num);
            tv_facescore_city = itemView.findViewById(R.id.tv_facescore_city);

        }
    }
}
