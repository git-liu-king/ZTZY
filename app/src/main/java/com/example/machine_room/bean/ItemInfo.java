package com.example.machine_room.bean;

/**
 * Created by 刘博 on 2020/8/3
 */
public class ItemInfo {
    private String mTitle;

    private String mContent;

    public ItemInfo(String pTitle, String pContent) {
        mTitle = pTitle;
        mContent = pContent;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String pTitle) {
        mTitle = pTitle;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String pContent) {
        mContent = pContent;
    }

    @Override
    public String toString() {
        return "ItemInfo{" +
                "mTitle='" + mTitle + '\'' +
                ", mContent='" + mContent + '\'' +
                '}';
    }
}
