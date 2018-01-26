package com.douglas.videolive.view.video.fragment;

import android.os.Bundle;

import com.douglas.videolive.base.BaseFragment;
import com.douglas.videolive.base.BaseView;
import com.douglas.videolive.model.logic.video.VideoOtherCateListLogic;
import com.douglas.videolive.model.logic.video.bean.VideoCateList;
import com.douglas.videolive.model.logic.video.bean.VideoReClassify;
import com.douglas.videolive.presenter.video.impl.VideoOtherCatePresenterImpl;
import com.douglas.videolive.presenter.video.interfaces.VideoOtherCateContract;
import com.douglas.videolive.view.video.adapter.videoReClassifyListAdapter;

import java.util.List;

/**
 * Created by shidongfang on 2018/1/26.
 * 视频页 列表页  显示 手游,娱乐,游戏,趣玩等
 */

public class OtherVideoFragment extends BaseFragment<VideoOtherCateListLogic,VideoOtherCatePresenterImpl>
                                implements VideoOtherCateContract.View{

    private String[] mTitles;
    private videoReClassifyListAdapter
    public static OtherVideoFragment getInstance(VideoCateList videoCateList,int position) {
        OtherVideoFragment rf = new OtherVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("mVideoCatelist",videoCateList);
        bundle.putInt("position",position);

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onInitView(Bundle bundle) {

    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected BaseView getViewImp() {
        return null;
    }

    @Override
    protected void lazyFetchData() {

    }

    @Override
    public void showErrorWithStatus(String msg) {

    }

    @Override
    public void getViewVideoOtherCate(List<VideoReClassify> cateLists) {

    }
}
