package com.douglas.videolive.base;

import android.content.Context;
import android.os.Bundle;

import com.douglas.videolive.model.ContractProxy;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shidongfang on 2017/11/30.
 */

public abstract class BaseActivity<M extends BaseModel, P extends BasePresenter> extends RxAppCompatActivity {
    protected P mPressenter;//定义Presenter
    protected Unbinder unbinder;
    protected Context mContext;

    protected abstract int getLayoutId();//获取资源文件

    protected abstract void onInitView(Bundle bundle);//初始化视图

    protected abstract void onEvent();//初始化时间Event

    protected abstract BaseView getView();//获取抽取view对象

    /**
     * 获得抽取接口Model对象
     *
     * @return
     */
    protected Class getModleClazz() {
        return (Class<M>) ContractProxy.getModelClazz(getClass(), 0);
    }

    /**
     * 获取接口Presenter对象
     *
     * @return
     */
    protected Class getPresenterClazz() {
        return (Class<P>) ContractProxy.getPresnterClazz(getClass(), 1);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            unbinder = ButterKnife.bind(this);
            bindMVP();
            onInitView(savedInstanceState);
            onEvent();
        }
    }

    private void bindMVP() {
        if (getPresenterClazz() != null) {
            mPressenter = getPresenterImpl();
            mPressenter.mContext = this;
            bindVM();
        }
    }

    private <T> T getPresenterImpl() {
        return ContractProxy.getInstance().presenter(getPresenterClazz());
    }

    private void bindVM() {
        if (mPressenter != null && mPressenter.isViewBind()
                && getModleClazz() != null && getView() != null) {
            ContractProxy.getInstance().bindModel(getModleClazz(), mPressenter);
            ContractProxy.getInstance().bindView(getView(), mPressenter);
            mPressenter.mContext = this;
        }
    }

    @Override
    protected void onStart() {
        if (mPressenter == null) {
            bindMVP();
        }
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }

        if (mPressenter != null) {
            ContractProxy.getInstance().unbindView(getView(), mPressenter);
            ContractProxy.getInstance().unbindModel(getModleClazz(), mPressenter);
            mPressenter = null;
        }
    }
}
