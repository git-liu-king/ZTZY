package com.example.machine_room.ui.fragment;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frame.base.BaseFragment;
import com.example.frame.base.IMainView;
import com.example.frame.base.MainModel;
import com.example.frame.utils.SharedPrefrenceUtils;
import com.example.machine_room.R;
import com.example.machine_room.adapter.NotAlarmRlvAdapter;
import com.example.machine_room.bean.DeviceInfo;
import com.example.machine_room.config.SpConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 刘博 on 2020/7/14
 */
public class NotAlarmFragment extends BaseFragment<MainModel> implements IMainView {

    @BindView(R.id.mNot_text)
    TextView mNotText;
    @BindView(R.id.mRlv)
    RecyclerView mRlv;
    @BindView(R.id.mRefreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private Context mContext;

    public static NotAlarmFragment getInstance() {
        NotAlarmFragment localFragment = new NotAlarmFragment();
        return localFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_not_alarm;
    }

    @Override
    protected MainModel initModel() {
        return new MainModel();
    }

    @Override
    protected void initView() {
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(false);

        mContext = getContext();
        List<DeviceInfo> localList = SharedPrefrenceUtils.getSerializableList(mContext, SpConfig.DEVICE_NOT_ALARM);
        if (localList != null && localList.size() > 0)  {
            mNotText.setVisibility(View.GONE);
            mRlv.setVisibility(View.VISIBLE);
            mRlv.setLayoutManager(new LinearLayoutManager(mContext));
            NotAlarmRlvAdapter localAdapter = new NotAlarmRlvAdapter(localList, mContext);
            mRlv.setAdapter(localAdapter);
        }else {
            mRlv.setVisibility(View.GONE);
            mNotText.setVisibility(View.VISIBLE);
        }

    }
}
