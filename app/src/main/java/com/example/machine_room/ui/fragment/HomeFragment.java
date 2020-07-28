package com.example.machine_room.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frame.base.BaseFragment;
import com.example.frame.base.BaseRlvAdapter;
import com.example.frame.base.IMainView;
import com.example.frame.base.MainModel;
import com.example.machine_room.R;
import com.example.machine_room.adapter.HomeRlvAdapter;
import com.example.machine_room.bean.RoomInfo;
import com.example.machine_room.config.IntentConfig;
import com.example.machine_room.ui.activity.DeviceActivity;
import com.example.machine_room.utils.LogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 刘博 on 2020/6/22
 */
public class HomeFragment extends BaseFragment<MainModel> implements IMainView, BaseRlvAdapter.OnItemClikListener {

    @BindView(R.id.mToolBar_title_text)
    TextView mToolBarTitleText;
    @BindView(R.id.mRlv)
    RecyclerView mHomeRlv;
    @BindView(R.id.mRefreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private Context mContext;
    private HomeRlvAdapter mAdapter;
    private ArrayList<RoomInfo> mList;

    public static HomeFragment getInstance() {
        HomeFragment localFragment = new HomeFragment();
        return localFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected MainModel initModel() {
        return new MainModel();
    }

    @Override
    protected void initView() {
        mToolBarTitleText.setText(R.string.home);
        mRefreshLayout.setEnableLoadMore(false);//禁止上拉加载
        mRefreshLayout.setEnableRefresh(false);//禁止下拉刷新

        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add(new RoomInfo("机房" + (i + 1),
                    "正常",
                    R.drawable.ic_launcher_background,
                    "查看设备",
                    "查看任务",
                    "报警信息",
                    "机房参数"));
        }

        mContext = getContext();
        mHomeRlv.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new HomeRlvAdapter(mList, mContext);
        mHomeRlv.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        mAdapter.setmItemClikListener(this);
    }

    @Override
    public void onItemClik(int position) {
        Intent localIntent = new Intent(mContext, DeviceActivity.class);
        localIntent.putExtra(IntentConfig.DEVICE_ROOM_NAME,mList.get(position).getName());
        startActivity(localIntent);
    }
}
