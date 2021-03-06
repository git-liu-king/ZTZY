package com.example.machine_room.bean;

import java.io.Serializable;

/**
 * Created by 刘博 on 2020/7/13
 */
public class DeviceInfo implements Serializable {

    private String mName;
    private String mStatus;
    private String mDetails;
    private String mAlarmText;
    private String mDate;
    private int mImage;
    private boolean mFlag;
    private boolean mTaskFlag;
    private String mTaskStatus;

    public DeviceInfo(String pName, String pStatus, String pDetails, String pAlarmText, String pDate, int pImage, boolean pFlag,boolean pTaskFlag,String pTaskStatus) {
        mName = pName;
        mStatus = pStatus;
        mDetails = pDetails;
        mAlarmText = pAlarmText;
        mDate = pDate;
        mImage = pImage;
        mFlag = pFlag;
        mTaskFlag = pTaskFlag;
        mTaskStatus = pTaskStatus;
    }

    public String getName() {
        return mName;
    }

    public void setName(String pName) {
        mName = pName;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String pStatus) {
        mStatus = pStatus;
    }

    public String getDetails() {
        return mDetails;
    }

    public void setDetails(String pDetails) {
        mDetails = pDetails;
    }

    public String getAlarmText() {
        return mAlarmText;
    }

    public void setAlarmText(String pAlarmText) {
        mAlarmText = pAlarmText;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String pDate) {
        mDate = pDate;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int pImage) {
        mImage = pImage;
    }

    public boolean isFlag() {
        return mFlag;
    }

    public void setFlag(boolean pFlag) {
        mFlag = pFlag;
    }

    public boolean isTaskFlag() {
        return mTaskFlag;
    }

    public void setTaskFlag(boolean pTaskFlag) {
        mTaskFlag = pTaskFlag;
    }

    public String getTaskStatus() {
        return mTaskStatus;
    }

    public void setTaskStatus(String pTaskStatus) {
        mTaskStatus = pTaskStatus;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "mName='" + mName + '\'' +
                ", mStatus='" + mStatus + '\'' +
                ", mDetails='" + mDetails + '\'' +
                ", mAlarmText='" + mAlarmText + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mImage=" + mImage +
                ", mFlag=" + mFlag +
                ", mTaskFlag=" + mTaskFlag +
                ", mTaskStatus='" + mTaskStatus + '\'' +
                '}';
    }
}
