package com.example.machine_room.ui.fragment;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.frame.base.BaseFragment;
import com.example.frame.base.IMainView;
import com.example.frame.base.MainModel;
import com.example.machine_room.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 刘博 on 2020/7/23
 */
public class NotReadFragment extends BaseFragment<MainModel> implements IMainView {

    @BindView(R.id.mNot_read)
    TextView mNotRead;
    @BindView(R.id.mRlv)
    RecyclerView mRlv;
    @BindView(R.id.mRefreshLayout)
    SmartRefreshLayout mRefreshLayout;

    public static NotReadFragment getInstance() {
        NotReadFragment localFragment = new NotReadFragment();
        return localFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_not_read;
    }

    @Override
    protected MainModel initModel() {
        return new MainModel();
    }

    @Override
    protected void initView() {
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(false);


    }
}
