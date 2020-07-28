package com.example.machine_room.bean;

/**
 * Created by 刘博 on 2020/7/10
 */
public class RoomInfo {
    private String mName;
    private String mStatus;
    private int mImage;
    private String mDevice;
    private String mTask;
    private String mAlarm;
    private String mParameter;

    public RoomInfo(String pName, String pStatus, int pImage, String pDevice, String pTask, String pAlarm, String pParameter) {
        mName = pName;
        mStatus = pStatus;
        mImage = pImage;
        mDevice = pDevice;
        mTask = pTask;
        mAlarm = pAlarm;
        mParameter = pParameter;
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

    public int getImage() {
        return mImage;
    }

    public void setImage(int pImage) {
        mImage = pImage;
    }

    public String getDevice() {
        return mDevice;
    }

    public void setDevice(String pDevice) {
        mDevice = pDevice;
    }

    public String getTask() {
        return mTask;
    }

    public void setTask(String pTask) {
        mTask = pTask;
    }

    public String getAlarm() {
        return mAlarm;
    }

    public void setAlarm(String pAlarm) {
        mAlarm = pAlarm;
    }

    public String getParameter() {
        return mParameter;
    }

    public void setParameter(String pParameter) {
        mParameter = pParameter;
    }
}
