package com.example.machine_room.base;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.frame.base.BaseApp;
import com.igexin.sdk.PushManager;

/**
 * Created by 刘博 on 2020/7/17
 */
public class RoomApp extends BaseApp {

    private static RoomApp mRoomApp;

    private static PushHandler mPushHandler;
    public static StringBuffer mPushSb = new StringBuffer();

    @Override
    public void onCreate() {
        super.onCreate();
        mRoomApp = this;
        if (mPushHandler == null) mPushHandler = new PushHandler();
        PushManager.getInstance().initialize(this);
    }

    public static RoomApp getRoomApp() {
        return mRoomApp;
    }

    public static class PushHandler extends Handler {

        public static final int RECEIVE_MESSAGE_DATA = 0; //接收到消息
        public static final int RECEIVE_CLIENT_ID = 1; //cid通知
        public static final int RECEIVE_ONLINE_STATE = 2; //cid在线状态通知消息
        public static final int SHOW_TOAST = 3; //Toast消息

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case RECEIVE_MESSAGE_DATA://接收到消息
                    mPushSb.append((String) msg.obj);
                    mPushSb.append("\n");
                    mPushSb.append("\r\n");
                    break;
                case RECEIVE_CLIENT_ID:
                    mPushSb.append(msg.obj);
                    break;
            }
        }
    }
}


