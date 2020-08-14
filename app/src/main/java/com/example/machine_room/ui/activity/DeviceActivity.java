package com.example.machine_room.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frame.base.BaseActivity;
import com.example.frame.base.IMainView;
import com.example.frame.base.MainModel;
import com.example.frame.utils.SharedPrefrenceUtils;
import com.example.machine_room.R;
import com.example.machine_room.adapter.DeviceRlvAdapter;
import com.example.machine_room.bean.DeviceInfo;
import com.example.machine_room.config.IntentConfig;
import com.example.machine_room.config.SpConfig;
import com.example.machine_room.utils.SimUtils;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 刘博 on 2020/7/13
 */
public class DeviceActivity extends BaseActivity<MainModel> implements IMainView, View.OnClickListener {

    @BindView(R.id.mToolBar_title_text)
    TextView mToolBarTitleText;
    @BindView(R.id.mDevice_room_image)
    CircleImageView mDeviceRoomImage;
    @BindView(R.id.mDevice_room_name)
    TextView mDeviceRoomName;
    @BindView(R.id.mDevice_room_num)
    TextView mDeviceRoomNum;
    @BindView(R.id.mDevice_person_name)
    TextView mDevicePersonName;
    @BindView(R.id.mDevice_person_phone)
    TextView mDevicePersonPhone;
    @BindView(R.id.mRlv)
    RecyclerView mRlv;
    @BindView(R.id.mRefreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.mLinear_call_phone)
    LinearLayout mLinearCallPhone;
    @BindView(R.id.mToolBar_left_image)
    ImageView mToolBarLeftImage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_device;
    }

    @Override
    protected MainModel initModel() {
        return new MainModel();
    }

    @Override
    protected void initView() {
        mToolBarLeftImage.setImageResource(R.mipmap.ic_arrow_back);
        mToolBarTitleText.setText(R.string.device);
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(false);
        mRlv.setFocusable(false);

        ArrayList<DeviceInfo> localList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            localList.add(
                    new DeviceInfo("设备" + (i + 1),
                            "正常",
                            "设备详情",
                            "警报信息",
                            "2020-8-20-9:00",
                            R.drawable.ic_launcher_background,
                            false,
                            false,
                            "检查"));
        }

        localList.get(5).setFlag(true);

        String localIntentName = getIntent().getStringExtra(IntentConfig.DEVICE_ROOM_NAME);
        if (!TextUtils.isEmpty(localIntentName)) mDeviceRoomName.setText(localIntentName);
        if (localList != null && localList.size() > 0)
            mDeviceRoomNum.setText(String.valueOf(localList.size()));
        mDeviceRoomImage.setImageResource(R.drawable.ic_launcher_background);
        mDevicePersonPhone.setText("联系电话:17613078977");
        mDevicePersonName.setText("负责人:LLL");

        SharedPrefrenceUtils.putSerializableList(this, SpConfig.DEVICE_DATA, localList);
        mRlv.setLayoutManager(new LinearLayoutManager(this));

        DeviceRlvAdapter localAdapter = new DeviceRlvAdapter(localList, this, DeviceRlvAdapter.DEVICE_ACTIVITY);
        mRlv.setAdapter(localAdapter);
    }

    @Override
    public void initListener() {
        mToolBarLeftImage.setOnClickListener(this);
        mLinearCallPhone.setOnClickListener(this);
    }

    @SuppressLint("HardwareIds")
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mToolBar_left_image:
                finish();
                break;
            case R.id.mLinear_call_phone:
                if (SimUtils.readSIMCard()) callPhone();
                else Toast.makeText(this, "请插入SIM卡", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void callPhone() {
        String localPhoneText = mDevicePersonPhone.getText().toString().trim();
        if (XXPermissions.hasPermission(this, Permission.CALL_PHONE)) {
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
        XXPermissions.with(this)
                .permission(Permission.CALL_PHONE)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean all) {
                        Toast.makeText(DeviceActivity.this, "已同意", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        Toast.makeText(DeviceActivity.this, "请同意该权限", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
