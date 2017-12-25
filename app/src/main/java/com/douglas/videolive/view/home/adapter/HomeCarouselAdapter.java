package com.douglas.videolive.view.home.adapter;

import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by shidongfang on 2017/12/25.
 */

public class HomeCarouselAdapter implements BGABanner.Adapter<SimpleDraweeView, String> {

    @Override
    public void fillBannerItem(BGABanner banner, SimpleDraweeView itemView, String model, int position) {
        itemView.setImageURI(Uri.parse(model));
    }
}
