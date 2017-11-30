package com.douglas.videolive.base;

import android.os.Bundle;

import com.douglas.videolive.model.ContractProxy;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shidongfang on 2017/11/30.
 */

public abstract class BaseActivity<M extends BaseModel,P extends > extends RxAppCompatActivity{
    protected P mPressenter;//定义Presenter
    protected Unbinder unbinder;

    protected  abstract  int getLayoutId();//获取资源文件

    protected abstract void onInitView(Bundle bundle);//初始化视图

    protected abstract void onEvent();//初始化时间Event

    protected abstract BaseView getView();//获取抽取view对象

    /**
     * 获得抽取接口Model对象
     * @return
     */
    protected Class getModleClazz () {
        return (Class<P>)ContractProxy.getModelClazz(getClass(),0);
    }

    /**
     * 获取接口Presenter对象
     * @return
     */
    protected Class getPresenterClazz(){
        return (Class<P>) ContractProxy.getPresnterClazz(getClass(), 1);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0){
            setContentView(getLayoutId());
            unbinder = ButterKnife.bind(this);
            bind
        }
    }

    private void bindMVP(){
        if (getPresenterClazz() != null) {
            mPressenter = getpre
        }
    }


}
