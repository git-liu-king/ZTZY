package com.example.machine_room.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frame.base.BaseRlvAdapter;
import com.example.frame.utils.SharedPrefrenceUtils;
import com.example.machine_room.R;
import com.example.machine_room.bean.DeviceInfo;
import com.example.machine_room.config.SpConfig;
import com.example.machine_room.ui.activity.ExceptionActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘博 on 2020/7/14
 */
public class DeviceRlvAdapter extends BaseRlvAdapter implements View.OnClickListener {

    private static final int CARD_TOP = 1;
    private static final int CARD_BOTTOM = 2;

    public static final int DEVICE_ACTIVITY = 1;
    public static final int NOTTASK_FRAGMENT = 2;

    private List<DeviceInfo> mList;
    private Context mContext;
    private ArrayList<DeviceInfo> mAlarmList;
    private int mFlag;
    private final LayoutInflater mInflater;
    private PopupWindow mWindow;
    private ArrayList<Boolean> mTaskFlag = new ArrayList<>();

    public DeviceRlvAdapter(List<DeviceInfo> pList, Context pContext, int pFlag) {
        mList = pList;
        mContext = pContext;
        mFlag = pFlag;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View localView = mInflater.inflate(R.layout.layout_device_rlv_item, parent, false);
        return new ItemHolder(localView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ItemHolder localHolder = (ItemHolder) holder;

        int localType = getItemViewType(position);
        if (localType != 0) {
            LinearLayout.LayoutParams localParams = (LinearLayout.LayoutParams) localHolder.mDeviceCard.getLayoutParams();
            if (localType == CARD_TOP) localParams.topMargin = 30;
            else if (localType == CARD_BOTTOM) localParams.bottomMargin = 30;
            localHolder.mDeviceCard.setLayoutParams(localParams);
        }

        if (mList.get(position).isFlag()) {
            mAlarmList = new ArrayList<>();
            mAlarmList.add(mList.get(position));
            localHolder.mDeviceStatus.setText("报警");
            SharedPrefrenceUtils.putSerializableList(mContext, SpConfig.DEVICE_NOT_ALARM, mAlarmList);
        } else localHolder.mDeviceStatus.setText(mList.get(position).getStatus());

        localHolder.mDeviceImage.setImageResource(mList.get(position).getImage());
        localHolder.mDeviceName.setText(mList.get(position).getName());
        localHolder.mDeviceDetails.setText(mList.get(position).getDetails());

        if (mFlag == DEVICE_ACTIVITY) {
            localHolder.mDeviceTask.setVisibility(View.GONE);
            localHolder.mDeviceView.setVisibility(View.VISIBLE);
            localHolder.mDeviceLinear.setVisibility(View.VISIBLE);
        } else if (mFlag == NOTTASK_FRAGMENT) {

            localHolder.mDeviceLinear.setVisibility(View.GONE);
            localHolder.mDeviceView.setVisibility(View.GONE);
            localHolder.mDeviceTask.setVisibility(View.VISIBLE);

            localHolder.mDeviceTask.setText(mList.get(position).getTaskStatus());

            View localView = mInflater.inflate(R.layout.layout_not_task_pop, null);
            mWindow = new PopupWindow(localView,
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            RelativeLayout localPopRelative = localView.findViewById(R.id.pop_relative);
            TextView localPopNormal = localView.findViewById(R.id.pop_normal);
            TextView localPopException = localView.findViewById(R.id.pop_exception);

            localHolder.mDeviceTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mList.get(position).isTaskFlag())
                        Toast.makeText(mContext, "该设备已检查", Toast.LENGTH_SHORT).show();
                    else showDialog(position);
                }
            });
            localPopRelative.setOnClickListener(this);
            localPopNormal.setOnClickListener(this);
            localPopException.setOnClickListener(this);
        }
    }

    public int getItemViewType(int position) {
        if (position == 0) return CARD_TOP;
        else if (position == mList.size() - 1) return CARD_BOTTOM;
        return 0;
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        /*switch (v.getId()) {
         *//*case R.id.device_task:
                //mWindow.showAtLocation(mRlv, Gravity.CENTER, 0, 0);

                break;*//*
            case R.id.pop_relative:
                mWindow.dismiss();
                break;
            case R.id.pop_exception:
                //跳转异常页面
                mWindow.dismiss();
                mContext.startActivity(new Intent(mContext, ExceptionActivity.class));
                break;
            case R.id.pop_normal:
                mWindow.dismiss();
                break;
        }*/
    }

    private void showDialog(final int pPosition) {

        AlertDialog.Builder localDialog = new AlertDialog.Builder(mContext);

        TextView localDialogText = new TextView(mContext);
        localDialogText.setText("该设备是否存在异常");
        localDialogText.setGravity(Gravity.CENTER);
        localDialogText.setTextSize(16);
        localDialogText.setPadding(0, 40, 0, 0);

        DialogInterface.OnClickListener localDialogOnClick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case AlertDialog.BUTTON_POSITIVE:
                        mContext.startActivity(new Intent(mContext, ExceptionActivity.class));
                        Toast.makeText(mContext, "存在", Toast.LENGTH_SHORT).show();
                        break;
                    case AlertDialog.BUTTON_NEGATIVE:
                        mList.get(pPosition).setTaskStatus("已检查");
                        mList.get(pPosition).setTaskFlag(true);
                        mTaskFlag.add(mList.get(pPosition).isTaskFlag());
                        notifyItemChanged(pPosition);
                        Toast.makeText(mContext, "不存在", Toast.LENGTH_SHORT).show();
                        break;
                    case AlertDialog.BUTTON_NEUTRAL:
                        Toast.makeText(mContext, "忽略", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        localDialog.setTitle("提示")
                .setView(localDialogText)
                .setIcon(R.drawable.ic_launcher_background)
                .setPositiveButton("存在", localDialogOnClick)
                .setNegativeButton("不存在", localDialogOnClick)
                .setNeutralButton("忽略", localDialogOnClick)
                .create()
                .show();
    }

    public boolean judgeTaskStatus() {
        if (mList != null && mList.size() > 0 && mTaskFlag != null && mTaskFlag.size() > 0) {
            if (mList.size() == mTaskFlag.size()) {
                SharedPrefrenceUtils.putSerializableList(mContext,SpConfig.DEVICE_TASK,mList);
                return true;
            } else return false;
        }

        return false;
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        private CardView mDeviceCard;
        private ImageView mDeviceImage;
        private TextView mDeviceName;
        private TextView mDeviceTask;
        private TextView mDeviceStatus;
        private View mDeviceView;
        private LinearLayout mDeviceLinear;
        private TextView mDeviceDetails;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            mDeviceCard = itemView.findViewById(R.id.device_card);
            mDeviceImage = itemView.findViewById(R.id.device_image);
            mDeviceName = itemView.findViewById(R.id.device_name);
            mDeviceTask = itemView.findViewById(R.id.device_task);
            mDeviceStatus = itemView.findViewById(R.id.device_status);
            mDeviceView = itemView.findViewById(R.id.device_view);
            mDeviceLinear = itemView.findViewById(R.id.device_linear);
            mDeviceDetails = itemView.findViewById(R.id.device_details);
        }
    }
}
