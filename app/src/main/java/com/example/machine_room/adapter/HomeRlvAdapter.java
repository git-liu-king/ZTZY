package com.example.machine_room.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frame.base.BaseRlvAdapter;
import com.example.machine_room.R;
import com.example.machine_room.bean.RoomInfo;
import com.example.machine_room.config.IntentConfig;
import com.example.machine_room.ui.activity.AlarmActivity;
import com.example.machine_room.ui.activity.DeviceActivity;
import com.example.machine_room.ui.activity.ParameterActivity;
import com.example.machine_room.ui.activity.TaskActivity;
import com.example.machine_room.utils.LogUtil;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 刘博 on 2020/7/10
 */
public class HomeRlvAdapter extends BaseRlvAdapter {

    private static final int CARD_TOP = 1;
    private static final int CARD_BOTTOM = 2;

    private static final int DEVICE_ACTIVITY = 1;
    private static final int ALARM_ACTIVITY = 2;
    private static final int TASK_ACTIVITY = 3;
    private static final int PARAMETER_ACTIVITY = 4;

    private ArrayList<RoomInfo> mList;
    private Context mContext;


    public HomeRlvAdapter(ArrayList<RoomInfo> pList, Context pContext) {
        mList = pList;
        mContext = pContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View localView = LayoutInflater.from(mContext).inflate(R.layout.layout_home_rlv_item, parent, false);
        return new ItemHolder(localView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ItemHolder localHolder = (ItemHolder) holder;

        int localType = getItemViewType(position);
        if (localType != 0) {
            LinearLayout.LayoutParams localParams = (LinearLayout.LayoutParams) localHolder.mHomeCard.getLayoutParams();
            if (localType == CARD_TOP) localParams.topMargin = 30;
            else if (localType == CARD_BOTTOM) localParams.bottomMargin = 30;
            localHolder.mHomeCard.setLayoutParams(localParams);
        }

        localHolder.mHomeItemImage.setImageResource(mList.get(position).getImage());
        localHolder.mHomeItemName.setText(mList.get(position).getName());
        localHolder.mHomeItemStatus.setText(mList.get(position).getStatus());
        localHolder.mHomeItemDevice.setText(mList.get(position).getDevice());
        localHolder.mHomeItemAlarm.setText(mList.get(position).getAlarm());
        localHolder.mHomeItemTask.setText(mList.get(position).getTask());
        localHolder.mHomeItemParameter.setText(mList.get(position).getParameter());
        localHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClikListener != null) mItemClikListener.onItemClik(position);
            }
        });
        skipActivity(localHolder.mHomeItemDevice,DEVICE_ACTIVITY,position);
        skipActivity(localHolder.mHomeItemAlarm,ALARM_ACTIVITY,position);
        skipActivity(localHolder.mHomeItemTask,TASK_ACTIVITY,position);
        skipActivity(localHolder.mHomeItemParameter,PARAMETER_ACTIVITY,position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return CARD_TOP;
        else if (position == mList.size() - 1) return CARD_BOTTOM;
        return 0;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private void skipActivity(View pView, final int pType, final int pPosition){
        pView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoomInfo localRoomInfo = mList.get(pPosition);
                Intent localIntent = new Intent();
                switch (pType){
                    case DEVICE_ACTIVITY:
                        localIntent.setClass(mContext,DeviceActivity.class);
                        localIntent.putExtra(IntentConfig.DEVICE_ROOM_NAME,localRoomInfo.getName());
                        break;
                    case ALARM_ACTIVITY:
                        localIntent.setClass(mContext, AlarmActivity.class);
                        break;
                    case TASK_ACTIVITY:
                        localIntent.setClass(mContext, TaskActivity.class);
                        localIntent.putExtra(IntentConfig.DEVICE_ROOM_NAME,localRoomInfo.getName());
                        break;
                    case PARAMETER_ACTIVITY:
                        localIntent.setClass(mContext, ParameterActivity.class);
                        break;
                }
                mContext.startActivity(localIntent);
            }
        });
    }


    class ItemHolder extends RecyclerView.ViewHolder {

        private CardView mHomeCard;
        private CircleImageView mHomeItemImage;
        private TextView mHomeItemName;
        private TextView mHomeItemStatus;
        private TextView mHomeItemDevice;
        private TextView mHomeItemAlarm;
        private TextView mHomeItemTask;
        private TextView mHomeItemParameter;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            mHomeCard = itemView.findViewById(R.id.home_card);
            mHomeItemImage = itemView.findViewById(R.id.home_item_image);
            mHomeItemName = itemView.findViewById(R.id.home_item_name);
            mHomeItemStatus = itemView.findViewById(R.id.home_item_status);
            mHomeItemDevice = itemView.findViewById(R.id.home_item_device);
            mHomeItemAlarm = itemView.findViewById(R.id.home_item_alarm);
            mHomeItemTask = itemView.findViewById(R.id.home_item_task);
            mHomeItemParameter = itemView.findViewById(R.id.home_item_parameter);
        }
    }
}
