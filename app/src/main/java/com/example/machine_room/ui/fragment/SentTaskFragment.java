package com.example.machine_room.ui.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.frame.base.BaseFragment;
import com.example.frame.base.IMainView;
import com.example.frame.base.MainModel;
import com.example.frame.utils.SharedPrefrenceUtils;
import com.example.machine_room.R;
import com.example.machine_room.adapter.ExpandableAdapter;
import com.example.machine_room.bean.DeviceInfo;
import com.example.machine_room.bean.ItemInfo;
import com.example.machine_room.config.SpConfig;
import com.example.machine_room.design.AnimatedExpandableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 刘博 on 2020/7/16
 */
public class SentTaskFragment extends BaseFragment<MainModel> implements IMainView, ExpandableListView.OnGroupClickListener {

    @BindView(R.id.mLv)
    AnimatedExpandableListView mLv;
    private Context mContext;
    private ExpandableAdapter mAdapter;

    public static SentTaskFragment getInstance() {
        SentTaskFragment localFragment = new SentTaskFragment();
        return localFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sent_task;
    }

    @Override
    protected MainModel initModel() {
        return new MainModel();
    }

    @Override
    protected void initView() {

        mLv.setGroupIndicator(null);

        mContext = getContext();
        List<DeviceInfo> mList = SharedPrefrenceUtils.getSerializableList(mContext, SpConfig.DEVICE_TASK);

        ArrayList<ArrayList<ItemInfo>> localItemList = new ArrayList<>();
        ArrayList<ItemInfo> localItemInfos = new ArrayList<>();
        localItemInfos.add(new ItemInfo("任务结束时间", "2020-8-" + (1 + 1) + "-10:00"));
        localItemInfos.add(new ItemInfo("延误天数", (1 + 1) + "天"));
        localItemInfos.add(new ItemInfo("异常项个数", (1 + 1) + "个"));
        localItemInfos.add(new ItemInfo("漏检项个数", (1 + 1) + "个"));
        if (mList != null && mList.size() > 0) {
            for (int i = 0; i < mList.size(); i++) {
                localItemList.add(localItemInfos);
            }
            mAdapter = new ExpandableAdapter(mList, localItemList, mContext);
            mLv.setAdapter(mAdapter);
        }
    }

    @Override
    protected void initListener() {
        mLv.setOnGroupClickListener(this);
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        if (mLv.isGroupExpanded(groupPosition)) {
            mLv.collapseGroupWithAnimation(groupPosition);
            mAdapter.setData(groupPosition,false);
        } else {
            mLv.expandGroupWithAnimation(groupPosition);
            mAdapter.setData(groupPosition,true);
        }
        return true;
    }
}
