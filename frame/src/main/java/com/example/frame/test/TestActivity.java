package com.example.frame.test;

import android.os.Bundle;
import android.widget.TextView;

import com.example.bean.TestInfo;
import com.example.frame.R;
import com.example.frame.R2;
import com.example.frame.base.BaseActivity;
import com.example.frame.base.IMainView;
import com.example.frame.base.MainModel;
import com.example.frame.constans.ApiConstans;
import com.example.frame.constans.LoadType;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 刘博 on 2020/6/22
 */
public class TestActivity extends BaseActivity<MainModel> implements IMainView {

    @BindView(R2.id.mText)
    TextView mText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected MainModel initModel() {
        return new MainModel();
    }

    @Override
    protected void initView() {}

    @Override
    public void initData() {
        mPresenter.getData(ApiConstans.TEST_API, LoadType.NORMAL);
    }

    @Override
    public void onSuccess(int whichApi, int dataType, Object[] t) {
        switch (whichApi) {
            case ApiConstans.TEST_API:
                TestInfo localInfo = (TestInfo) t[0];
                mText.setText(localInfo.getData());
                break;
        }
    }
}
