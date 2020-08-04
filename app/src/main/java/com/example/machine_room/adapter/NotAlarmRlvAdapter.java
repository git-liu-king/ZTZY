package com.example.machine_room.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frame.base.BaseRlvAdapter;
import com.example.frame.utils.SharedPrefrenceUtils;
import com.example.machine_room.R;
import com.example.machine_room.bean.DeviceInfo;
import com.example.machine_room.config.SpConfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;

/**
 * Created by 刘博 on 2020/7/15
 */
public class NotAlarmRlvAdapter extends BaseRlvAdapter {

    private static final int CARD_TOP = 1;
    private static final int CARD_BOTTOM = 2;

    private List<DeviceInfo> mList;
    private Context mContext;

    public NotAlarmRlvAdapter(List<DeviceInfo> pList, Context pContext) {
        mList = pList;
        mContext = pContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View localView = LayoutInflater.from(mContext).inflate(R.layout.layout_not_alarm_item, parent, false);
        return new ItemHolder(localView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ItemHolder localHolder = (ItemHolder) holder;

        int localType = getItemViewType(position);
        if (localType != 0) {
            LinearLayout.LayoutParams localParams = (LinearLayout.LayoutParams) localHolder.mNotAlarmCard.getLayoutParams();
            if (localType == CARD_TOP) localParams.topMargin = 30;
            else if (localType == CARD_BOTTOM) localParams.bottomMargin = 30;
            localHolder.mNotAlarmCard.setLayoutParams(localParams);
        }

        localHolder.mNotAlarmImage.setImageResource(mList.get(position).getImage());
        localHolder.mNotAlarmName.setText(mList.get(position).getName());
        localHolder.mNotAlarmNotOrSent.setText("确定");
        localHolder.mNotAlarmContent.setText(mList.get(position).getAlarmText());
        localHolder.mNotAlarmDate.setText(mList.get(position).getDate());

        localHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClikListener != null) mItemClikListener.onItemClik(position);
            }
        });

        localHolder.mNotAlarmNotOrSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeviceInfo localInfo = mList.get(position);
                SharedPrefrenceUtils.putObject(mContext,SpConfig.DEVICE_SENT_ALARM,localInfo);
                List<DeviceInfo> localList = SharedPrefrenceUtils.getSerializableList(mContext, SpConfig.DEVICE_NOT_ALARM);
                localList.remove(position);
                mList.remove(localInfo);
                SharedPrefrenceUtils.putSerializableList(mContext,SpConfig.DEVICE_NOT_ALARM,localList);
                notifyDataSetChanged();
            }
        });
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

    class ItemHolder extends RecyclerView.ViewHolder {

        private CardView mNotAlarmCard;
        private CircleImageView mNotAlarmImage;
        private TextView mNotAlarmName;
        private TextView mNotAlarmNotOrSent;
        private TextView mNotAlarmContent;
        private TextView mNotAlarmDate;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            mNotAlarmCard = itemView.findViewById(R.id.notAlarm_card);
            mNotAlarmImage = itemView.findViewById(R.id.notAlarm_image);
            mNotAlarmName = itemView.findViewById(R.id.notAlarm_name);
            mNotAlarmNotOrSent = itemView.findViewById(R.id.notAlarm_notOrSent);
            mNotAlarmDate = itemView.findViewById(R.id.notAlarm_date);
        }
    }
}
