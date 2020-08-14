package com.example.machine_room.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.frame.base.BaseActivity;
import com.example.frame.base.IMainView;
import com.example.frame.base.MainModel;
import com.example.machine_room.R;
import com.example.machine_room.utils.CameraUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 刘博 on 2020/7/13
 */
public class ParameterActivity extends BaseActivity<MainModel> implements IMainView, View.OnClickListener {

    @BindView(R.id.mToolBar_title_text)
    TextView mToolBarTitleText;
    @BindView(R.id.btn_open)
    Button btnOpen;
    @BindView(R.id.btn_close)
    Button btnClose;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_parameter;
    }

    @Override
    protected MainModel initModel() {
        return new MainModel();
    }

    @Override
    protected void initView() {
        mToolBarTitleText.setText("详情");
    }

    @Override
    public void initListener() {
        btnClose.setOnClickListener(this);
        btnOpen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_close:
                CameraUtil.changeFlashLight(false);
                break;
            case R.id.btn_open:
                CameraUtil.changeFlashLight(true);
                break;
        }
    }
}
