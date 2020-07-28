package com.example.machine_room.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frame.base.BaseFragment;
import com.example.frame.base.IMainView;
import com.example.frame.base.MainModel;
import com.example.frame.utils.SharedPrefrenceUtils;
import com.example.machine_room.R;
import com.example.machine_room.adapter.DeviceRlvAdapter;
import com.example.machine_room.bean.DeviceInfo;
import com.example.machine_room.config.SpConfig;
import com.example.machine_room.utils.SimUtils;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 刘博 on 2020/7/16
 */
public class NotTaskFragment extends BaseFragment<MainModel> implements IMainView, View.OnClickListener {

    private static final String ROOM_NAME = "ROOM_NAME";
    @BindView(R.id.mTask_room_image)
    CircleImageView mTaskRoomImage;
    @BindView(R.id.mTask_room_name)
    TextView mTaskRoomName;
    @BindView(R.id.mTask_room_date)
    TextView mTaskRoomDate;
    @BindView(R.id.mTask_person_name)
    TextView mTaskPersonName;
    @BindView(R.id.mTask_person_phone)
    TextView mTaskPersonPhone;
    @BindView(R.id.mLinear_call_phone)
    LinearLayout mLinearCallPhone;
    @BindView(R.id.mRlv)
    RecyclerView mRlv;
    @BindView(R.id.mRefreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private Context mContext;

    public static NotTaskFragment getInstance(String pName) {
        NotTaskFragment localFragment = new NotTaskFragment();

        Bundle localBundle = new Bundle();
        localBundle.putString(ROOM_NAME, pName);
        localFragment.setArguments(localBundle);
        return localFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_not_task;
    }

    @Override
    protected MainModel initModel() {
        return new MainModel();
    }

    @Override
    protected void initView() {

        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(false);

        Bundle localBundle = getArguments();
        String localRoomName = null;
        if (localBundle != null) {
            localRoomName = localBundle.getString(ROOM_NAME);
        }

        mContext = getContext();
        List<DeviceInfo> localList = SharedPrefrenceUtils.getSerializableList(mContext, SpConfig.DEVICE_DATA);

        mTaskRoomImage.setImageResource(R.drawable.ic_launcher_background);
        if (!TextUtils.isEmpty(localRoomName)) mTaskRoomName.setText(localRoomName);
        mTaskRoomDate.setText("2020-7-16-9:00");
        mTaskPersonName.setText("负责人:LLL");
        mTaskPersonPhone.setText("联系电话:15136793835");

        if (localList != null && localList.size() > 0) {
            mRlv.setLayoutManager(new LinearLayoutManager(mContext));
            DeviceRlvAdapter localAdapter = new DeviceRlvAdapter(localList, mContext, mRlv, DeviceRlvAdapter.NOTTASK_FRAGMENT);
            mRlv.setAdapter(localAdapter);
            localAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void initListener() {
        mLinearCallPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mLinear_call_phone:
                if (SimUtils.readSIMCard()) callPhone();
                else Toast.makeText(mContext, "请插入SIM卡", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void callPhone() {

        if (XXPermissions.hasPermission(mContext, Permission.CALL_PHONE)) {
            String localPhoneText = mTaskPersonPhone.getText().toString().trim();
            if (!TextUtils.isEmpty(localPhoneText)) {
                String[] localPhone = localPhoneText.split(":");
                if (!TextUtils.isEmpty(localPhone[1])) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + localPhone[1]));
                    startActivity(intent);
                }
            }
        } else requestPermission();
    }

    private void requestPermission() {
        XXPermissions.with(getActivity())
                .permission(Permission.CALL_PHONE)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean all) {
                        Toast.makeText(mContext, "已同意", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        Toast.makeText(mContext, "请同意该权限", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
