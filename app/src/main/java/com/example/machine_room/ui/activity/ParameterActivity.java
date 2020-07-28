package com.example.machine_room.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.frame.base.BaseActivity;
import com.example.frame.base.IMainView;
import com.example.frame.base.MainModel;
import com.example.machine_room.R;

import java.nio.Buffer;

/**
 * Created by 刘博 on 2020/7/13
 */
public class ParameterActivity extends BaseActivity<MainModel> implements IMainView {

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

    }
}
