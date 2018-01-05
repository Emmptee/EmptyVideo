package com.douglas.videolive.view.live.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.douglas.videolive.R;
import com.douglas.videolive.model.logic.live.bean.LiveOtherTwoColumn;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class LivePopupListAdapter extends BaseAdapter {

    private List<LiveOtherTwoColumn> mLiveColumn;
    private LayoutInflater mInflater;
    private Context context;

    public LivePopupListAdapter(Context context, List<LiveOtherTwoColumn> mLiveColumnder) {
        this.context = context;
        this.mLiveColumn = mLiveColumnder;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mLiveColumn.size();
    }

    @Override
    public Object getItem(int position) {
        return mLiveColumn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_popup_live_gridview, viewGroup, false);
            holder = new ViewHolder();
            holder.proName = (TextView) view.findViewById(R.id.tv_item_name);
            holder.imgIcon = (SimpleDraweeView) view.findViewById(R.id.img_item_gridview);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.proName.setText(mLiveColumn.get(position).getTag_name());
        holder.imgIcon.setImageURI(Uri.parse(mLiveColumn.get(position).getIcon_url()));
        return view;
    }

    /**
     * 定义ViewHodle
     */
    class ViewHolder {
        private TextView proName;

        private SimpleDraweeView imgIcon;

    }

}
