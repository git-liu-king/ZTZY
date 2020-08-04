package com.example.machine_room.ui.fragment;

import com.example.frame.base.BaseFragment;
import com.example.frame.base.IMainView;
import com.example.frame.base.MainModel;
import com.example.machine_room.R;

/**
 * Created by 刘博 on 2020/7/23
 */
public class SentReadFragment extends BaseFragment<MainModel> implements IMainView {

    public static SentReadFragment getInstance() {
        SentReadFragment localFragment = new SentReadFragment();
        return localFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sent_read;
    }

    @Override
    protected MainModel initModel() {
        return new MainModel();
    }

    @Override
    protected void initView() {

    }
}
