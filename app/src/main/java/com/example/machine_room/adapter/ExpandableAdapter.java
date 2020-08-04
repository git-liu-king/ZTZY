package com.example.machine_room.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frame.base.BaseApp;
import com.example.frame.utils.SharedPrefrenceUtils;
import com.example.machine_room.R;
import com.example.machine_room.bean.DeviceInfo;
import com.example.machine_room.bean.ItemInfo;
import com.example.machine_room.config.SpConfig;
import com.example.machine_room.design.AnimatedExpandableListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘博 on 2020/8/3
 */
public class ExpandableAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    private List<DeviceInfo> mGroupList;
    private ArrayList<ArrayList<ItemInfo>> mItemList;
    private Context mContext;

    public ExpandableAdapter(List<DeviceInfo> pGroupList, ArrayList<ArrayList<ItemInfo>> pItemList, Context pContext) {
        mGroupList = pGroupList;
        mItemList = pItemList;
        mContext = pContext;
    }

    @Override
    public int getGroupCount() {
        return mGroupList.size();
    }


    @Override
    public Object getGroup(int groupPosition) {
        return mGroupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mItemList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder localHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_sent_task_group,parent,false);
            localHolder = new GroupHolder();
            localHolder.mTaskGroupAffirm = convertView.findViewById(R.id.task_group_affirm);
            localHolder.mTaskGroupDate = convertView.findViewById(R.id.task_group_date);
            localHolder.mTaskGroupImage = convertView.findViewById(R.id.Task_group_image);
            convertView.setTag(localHolder);
        }else localHolder = (GroupHolder) convertView.getTag();

        localHolder.mTaskGroupImage.setImageResource(mGroupList.get(groupPosition).getImage());
        localHolder.mTaskGroupDate.setText(mGroupList.get(groupPosition).getDate());
        if (SharedPrefrenceUtils.getBoolean(mContext, SpConfig.TASK_FLAG))
            localHolder.mTaskGroupAffirm.setText("完成");
        else {
            localHolder.mTaskGroupAffirm.setTextColor(BaseApp.getRes().getColor(R.color.colorRed));
            localHolder.mTaskGroupAffirm.setText("未完成");
        }
        return convertView;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemHolder localHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_sent_task_item,parent,false);
            localHolder = new ItemHolder();
            localHolder.mTaskItemTitle = convertView.findViewById(R.id.task_item_title);
            localHolder.mTaskItemContent = convertView.findViewById(R.id.task_item_content);
            convertView.setTag(localHolder);
        }else localHolder = (ItemHolder) convertView.getTag();

        ItemInfo localInfo = mItemList.get(groupPosition).get(childPosition);
        localHolder.mTaskItemTitle.setText(localInfo.getTitle());
        localHolder.mTaskItemContent.setText(localInfo.getContent());
        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return mItemList.get(groupPosition).size();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void initView() {
    }

    class GroupHolder{
        TextView mTaskGroupDate;
        TextView mTaskGroupAffirm;
        ImageView mTaskGroupImage;
    }

    class ItemHolder{
        TextView mTaskItemTitle;
        TextView mTaskItemContent;
    }

}
