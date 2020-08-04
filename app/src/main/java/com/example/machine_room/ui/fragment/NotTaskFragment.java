package com.example.machine_room.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
    @BindView(R.id.mTask_btn_affirm)
    TextView mTaskBtnAffirm;
    private Context mContext;
    private DeviceRlvAdapter mAdapter;
    private List<DeviceInfo> mList;

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
        mList = SharedPrefrenceUtils.getSerializableList(mContext, SpConfig.DEVICE_DATA);

        mTaskRoomImage.setImageResource(R.drawable.ic_launcher_background);
        if (!TextUtils.isEmpty(localRoomName)) mTaskRoomName.setText(localRoomName);
        mTaskRoomDate.setText("2020-7-16-9:00");
        mTaskPersonName.setText("负责人:LLL");
        mTaskPersonPhone.setText("联系电话:15136793835");

        if (mList != null && mList.size() > 0) {
            mRlv.setLayoutManager(new LinearLayoutManager(mContext));
            mAdapter = new DeviceRlvAdapter(mList, mContext, DeviceRlvAdapter.NOTTASK_FRAGMENT);
            mRlv.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void initListener() {
        mLinearCallPhone.setOnClickListener(this);
        mTaskBtnAffirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mLinear_call_phone:
                if (SimUtils.readSIMCard()) callPhone();
                else Toast.makeText(mContext, "请插入SIM卡", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mTask_btn_affirm:
                affirmTask();
                break;
        }
    }

    private void affirmTask() {
        if (mAdapter != null) {
            if (mAdapter.judgeTaskStatus()) {
                SharedPrefrenceUtils.saveBoolean(mContext,SpConfig.TASK_FLAG,true);
                Toast.makeText(mContext, "已完成", Toast.LENGTH_SHORT).show();
            } else {
                showDialog();
            }
        }
    }

    private void showDialog() {
        new AlertDialog.Builder(mContext)
                .setTitle("提示")
                .setIcon(R.drawable.ic_launcher_background)
                .setMessage("该任务还有未检查的设备,是否结束任务")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPrefrenceUtils.saveBoolean(mContext,SpConfig.TASK_FLAG,false);
                        SharedPrefrenceUtils.putSerializableList(mContext,SpConfig.DEVICE_TASK,mList);
                        Toast.makeText(mContext, "结束", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "未结束", Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();
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
