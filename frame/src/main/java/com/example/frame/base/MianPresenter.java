package com.example.frame.base;

import java.lang.ref.WeakReference;

public class MianPresenter<V extends IMainView, M extends IMianModel> implements IMainPersenter {

    private WeakReference<M> mModel;
    private WeakReference<V> mView;

    public MianPresenter(V pView, M pModel) {
        mModel = new WeakReference<>(pModel);
        mView = new WeakReference<>(pView);
    }

    @Override
    public void getData(int whichApi, int dataType, Object... mPS) {
        if (mModel != null && mModel.get() != null)
            mModel.get().getData(this, whichApi, dataType, mPS);
    }

    @Override
    public synchronized void onSuccess(int whichApi, int datatype, Object[] t) {
        if (mView != null && mView.get() != null)
            mView.get().onSuccess(whichApi, datatype, t);
    }

    @Override
    public void onFail(int whichApi, String msg) {
        if (mView != null && mView.get() != null)
            mView.get().onFail(whichApi, msg);
    }

    public void onDestroy() {
        if (mView != null && mView.get() != null) mView.clear();
        if (mModel != null && mModel.get() != null) mModel.clear();
    }
}
