package com.example.machine_room.ui.activity;

import com.example.frame.base.BaseActivity;
import com.example.frame.base.IMainView;
import com.example.frame.base.MainModel;
import com.example.machine_room.R;

/**
 * Created by 刘博 on 2020/7/23
 */
public class ExceptionActivity extends BaseActivity<MainModel> implements IMainView {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_exception;
    }

    @Override
    protected MainModel initModel() {
        return new MainModel();
    }

    @Override
    protected void initView() {

    }
}
