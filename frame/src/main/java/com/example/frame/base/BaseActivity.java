package com.example.frame.base;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.frame.R;
import com.example.frame.utils.StatusBarUtils;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import butterknife.ButterKnife;

public abstract class BaseActivity<M extends IMianModel> extends AppCompatActivity implements IMainView {

    public MianPresenter mPresenter;
    private M mModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setStatusBarTitleColor(this, true);
        StatusBarUtils.setTransparent(this);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        mModel = initModel();
        mPresenter = new MianPresenter(this,mModel);
        initData();
        initListener();
    }

    protected abstract int getLayoutId();

    protected abstract M initModel();

    protected abstract void initView();

    public void initData() {

    }

    public void initListener() {

    }

    @Override
    public void onSuccess(int whichApi, int dataType, Object[] t) {

    }

    @Override
    public void onFail(int whichApi, String msg) {
        showErrorLog("错误位置:"+whichApi+"错误信息:"+msg);
    }

    public void showErrorLog(Object object){
        Log.e(this.getClass().getName(), object.toString() );
    }

    @SuppressLint("ResourceAsColor")
    public void loading(boolean pFlag){
        if (pFlag){
            ZLoadingDialog localDialog = new ZLoadingDialog(this);
            localDialog
                    .setLoadingBuilder(Z_TYPE.DOUBLE_CIRCLE)
                    .setDialogBackgroundColor(Color.parseColor("#989498"))
                    .setLoadingColor(R.color.colorAccent)
                    .setHintText("loading...")
                    .setDurationTime(0.5)
                    .show();

        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
}
