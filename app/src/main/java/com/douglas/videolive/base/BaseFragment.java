package com.douglas.videolive.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.douglas.videolive.model.ContractProxy;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shidongfang on 2017/12/6.
 */

public abstract class BaseFragment<M extends BaseModel,P extends BasePresenter> extends RxFragment{
    private static final String TAG = "BaseFragment";
    protected Unbinder unbinder;
    protected View rootView;
    protected Context mContext = null;
    private boolean isViewPrepared;
    private boolean hasFetchData;//已经出发懒加载数据

    protected P mPresenter;

    protected abstract int getLayoutId();//获取布局的资源文件

    protected abstract void onInitView(Bundle bundle);//初始化数据

    protected abstract void onEvent();//初始化事件

    protected abstract BaseView getViewImp();//获取抽取view对象

    //获取抽取借口的Model对象
    protected Class getModelClazz(){
        return ContractProxy.getModelClazz(getClass(),0);
    }

    //获取抽取接口的Presenter对象
    protected Class getPressenterClazz(){
        return ContractProxy.getPresnterClazz(getClass(),0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView!= null){
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null){
                parent.removeView(rootView);
            }
        }

        if (getLayoutId()!= 0){
            rootView= inflater.inflate(getLayoutId(),container,false);
        }else {
            rootView= super.onCreateView(inflater,container,savedInstanceState);

        }

        unbinder= ButterKnife.bind(this,rootView);
        bindMVP();
        onInitView(savedInstanceState);
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            lazyFetchDataIfPrepared();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onEvent();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepared = true;
        lazyFetchDataIfPrepared();
        if (mPresenter == null) {
            bindMVP();
        }
    }

    /**
     * 懒加载
     */
    protected void lazyFetchDataIfPrepared(){
        //用户可见fragment && 没有加载过数据 && 视图已经准备完毕了
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared){
            hasFetchData = true;
            lazyFetchData();
        }
    }

    /**
     * 懒加载获取数据，仅在满足fragment可见和视图准备好的时候调用一次
     */
    protected abstract void lazyFetchData();

    @Override
    public void onStart() {
        if (mPresenter == null) {
            bindMVP();
        }
        super.onStart();
    }

    private void bindMVP(){
        if (getPressenterClazz()!= null){
            mPresenter =getPresenterImpl();
            mPresenter.mContext = getActivity();
            bindVM();
        }
    }

    private void bindVM(){
        if (mPresenter != null && !mPresenter.isViewBind()
                && getModelClazz() != null && getViewImp() != null) {
            ContractProxy.getInstance().bindModel(getModelClazz(),mPresenter);
            ContractProxy.getInstance().bindView(getViewImp(),mPresenter);
            mPresenter.mContext =getActivity();
        }
    }

    private <T> T getPresenterImpl(){
        return ContractProxy.getInstance().presenter(getPressenterClazz());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mPresenter != null) {
            ContractProxy.getInstance().unbindView(getViewImp(),mPresenter);
            ContractProxy.getInstance().unbindModel(getModelClazz(),mPresenter);
        }
    }
}
