package com.example.frame.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 刘博 on 2020/6/22
 */
public abstract class BaseFragment<M extends IMianModel> extends Fragment implements IMainView {

    public MianPresenter mPresenter;
    private M mModel;
    private Unbinder mBind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View localView = inflater.inflate(getLayoutId(), container, false);
        mBind = ButterKnife.bind(this, localView);
        return localView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        mModel = initModel();
        mPresenter = new MianPresenter(this, mModel);
        initData();
        initListener();
    }

    protected abstract int getLayoutId();

    protected abstract M initModel();

    protected abstract void initView();

    protected void initData() {

    }

    protected void initListener() {
    }

    @Override
    public void onSuccess(int whichApi, int dataType, Object[] t) {

    }

    @Override
    public void onFail(int whichApi, String msg) {
        showErrorLog("错误位置:" + whichApi + "错误信息:" + msg);
    }

    public void showErrorLog(Object object){
        Log.e(this.getClass().getName(), object.toString() );
    }

    @Override
    public void onDestroyView() {
        if (mBind != null) mBind = null;
        mPresenter.onDestroy();
        super.onDestroyView();
    }


}
