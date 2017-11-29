package com.douglas.videolive.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.gesture.Gesture;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.douglas.videolive.R;
import com.douglas.videolive.utils.ThemeUtils;

import java.util.ArrayList;
import java.util.List;


public class NativeTabBar extends LinearLayout implements View.OnClickListener {
    private static final String KEY_CURRENT_TAG = "com.startsmake.template.currentTag";

    private List<ViewHolder> mViewHolderList;
    private OnTabSelectedListener mTabSelectListener;
    private FragmentActivity mFragmentActivity;
    private String mCurrentTag;
    private String mRestoreTag;
    private int mMainContentLayoutId;
    private ColorStateList mSelectedTextColor;
    private ColorStateList mNomalTextColor;
    private float mTabTextSize;
    private int mDefaultSelectedTab = 0;
    private int mCurrentSelectedTab;

    public NativeTabBar(Context context) {
        super(context);
    }

    public NativeTabBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NativeTabBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.NavigateTabBar, 0, 0);
        ColorStateList tabTextColor = typedArray.getColorStateList(R.styleable.NavigateTabBar_navigateTabTextColor);
        ColorStateList selectedTabTextColor = typedArray.getColorStateList(R.styleable.NavigateTabBar_navigateTabSelectedTextColor);
        mTabTextSize = typedArray.getDimensionPixelSize(R.styleable.NavigateTabBar_navigateTabTextSize, 0);
        mMainContentLayoutId = typedArray.getResourceId(R.styleable.NavigateTabBar_containerId, 0);
        mNomalTextColor = (tabTextColor != null ? tabTextColor : context.getResources().getColorStateList(R.color.tab_text_normal));

        if (selectedTabTextColor != null) {
            mSelectedTextColor = selectedTabTextColor;
        } else {
            ThemeUtils.checkAppCompatTheme(context);
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
            mSelectedTextColor = context.getResources().getColorStateList(typedValue.resourceId);
        }

        mViewHolderList = new ArrayList<>();

    }

    public void addTab(Class frameLayoutClass, TabParam tabParam) {
        int defaultLayout = R.layout.comui_tab_view;
        if (TextUtils.isEmpty(tabParam.title)) {
            tabParam.title = getContext().getString(tabParam.titleStringRes);
        }

        View view = LayoutInflater.from(getContext()).inflate(defaultLayout, null);
        view.setFocusable(true);

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.tabIndex = mViewHolderList.size();
        viewHolder.fragmentClass = frameLayoutClass;
        viewHolder.tag = tabParam.title;
        viewHolder.pageParam = tabParam;
        viewHolder.tabIcon = (ImageView) findViewById(R.id.tab_icon);
        viewHolder.tabTitle = (TextView) findViewById(R.id.tab_title);

        if (TextUtils.isEmpty(tabParam.title)) {
            viewHolder.tabTitle.setVisibility(INVISIBLE);
        } else {
            viewHolder.tabTitle.setText(tabParam.title);
        }

        if (mTabTextSize != 0) {
            viewHolder.tabTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTabTextSize);
        }
        
        if (mNomalTextColor != null) {
            viewHolder.tabTitle.setTextColor(mNomalTextColor);
        }
        
        if (tabParam.backgroundColor >0){
            view.setBackgroundResource(tabParam.backgroundColor);
        }
        
        if (tabParam.iconResId > 0){
            viewHolder.tabIcon.setImageResource(tabParam.iconResId);
        }else {
            viewHolder.tabIcon.setVisibility(INVISIBLE);
        }
        
        if (tabParam.iconResId >0 && tabParam.iconSekectedResId > 0){
            view.setTag(viewHolder);
            view.setOnClickListener(this);
            mViewHolderList.add(viewHolder);
        }
        
        addView(view,new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1.0f));
        
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mMainContentLayoutId == 0) {
            throw new RuntimeException("mFrameLayoutId Cannot be 0");
        }
        if (mViewHolderList.size() == 0) {
            throw new RuntimeException("mViewHolderList.size Cannot be 0, Please call addTab()");
        }
        if (!(getContext() instanceof FragmentActivity)) {
            throw new RuntimeException("parent activity must is extends FragmentActivity");
        }
        
        mFragmentActivity = (FragmentActivity) getContext();
        
        ViewHolder defaultHolder = null;
        hideAllFragment();

        if (!TextUtils.isEmpty(mRestoreTag)){
            for (ViewHolder viewHolder : mViewHolderList) {
                if (TextUtils.equals(mRestoreTag,viewHolder.tag));{
                    defaultHolder = viewHolder;
                    mRestoreTag = null;
                    break;
                }
            }
        }else {
            defaultHolder = mViewHolderList.get(mDefaultSelectedTab);
        }

        showfr
        
    }

    public NativeTabBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onClick(View v) {

    }

    public static class ViewHolder {
        public String tag;
        public TabParam pageParam;
        public ImageView tabIcon;
        public TextView tabTitle;
        public Class fragmentClass;
        public int tabIndex;
    }

    public void showFragment(ViewHolder holder){
        FragmentTransaction transaction = mFragmentActivity.getSupportFragmentManager().beginTransaction();
        if (isFragmentShown(transaction,holder.tag)){
            return;
        }

        setc
    }

    private boolean isFragmentShown(FragmentTransaction transaction, String newTag){
        if (TextUtils.equals(newTag,mCurrentTag)){
            return true;
        }

        if (TextUtils.isEmpty(mCurrentTag)){
            return false;
        }

        Fragment fragment = mFragmentActivity.getSupportFragmentManager().findFragmentByTag(mCurrentTag);
        if (fragment != null && !fragment.isHidden()){
            transaction.hide(fragment);
        }

        return false;
    }

    private void hideAllFragment(){
        if (mViewHolderList == null || mViewHolderList.size() == 0) {
            return;
        }

        FragmentTransaction transaction = mFragmentActivity.getSupportFragmentManager().beginTransaction();

        for (ViewHolder viewHolder : mViewHolderList) {
            Fragment fragment = mFragmentActivity.getSupportFragmentManager().findFragmentByTag(viewHolder.tag);
            if (fragment != null && !fragment.isHidden()) {
                transaction.hide(fragment);
            }
        }
        transaction.commit();
    }

    public static class TabParam {
        public int backgroundColor = android.R.color.white;
        public int iconResId;
        public int iconSekectedResId;
        public int titleStringRes;
        public String title;

        public TabParam(int iconResId, int iconSekectedResId, String title) {
            this.iconResId = iconResId;
            this.iconSekectedResId = iconSekectedResId;
            this.title = title;
        }

        public TabParam(int backgroundColor, int iconResId, int iconSekectedResId, int titleStringRes, String title) {
            this.backgroundColor = backgroundColor;
            this.iconResId = iconResId;
            this.iconSekectedResId = iconSekectedResId;
            this.titleStringRes = titleStringRes;
            this.title = title;
        }

        public TabParam(int backgroundColor, int iconResId, int iconSekectedResId, String title) {
            this.backgroundColor = backgroundColor;
            this.iconResId = iconResId;
            this.iconSekectedResId = iconSekectedResId;
            this.title = title;
        }

        public TabParam(int backgroundColor, int iconResId, int iconSekectedResId, int titleStringRes) {

            this.backgroundColor = backgroundColor;
            this.iconResId = iconResId;
            this.iconSekectedResId = iconSekectedResId;
            this.titleStringRes = titleStringRes;
        }

        public TabParam(int iconResId, int iconSekectedResId, int titleStringRes) {

            this.iconResId = iconResId;
            this.iconSekectedResId = iconSekectedResId;
            this.titleStringRes = titleStringRes;
        }
    }

    public interface OnTabSelectedListener {
        void onTabSelected(ViewHolder holder);
    }

    public void setTabSelectListener(OnTabSelectedListener tabSelectListener) {
        mTabSelectListener = tabSelectListener;
    }
}
