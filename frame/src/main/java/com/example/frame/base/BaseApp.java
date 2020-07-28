package com.example.frame.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.example.frame.net.DeviceUuidFactory;
import com.example.frame.utils.SharedPrefrenceUtils;

import java.util.UUID;

public class BaseApp extends Application {

    public static BaseApp mBaseApp;
    public UUID mUuid;
    public String mToken;
    public static String mSeesion = "";

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApp = this;
        mUuid = DeviceUuidFactory.getInstance(getApplicationContext()).getDeviceUuid();
        String session = SharedPrefrenceUtils.getString(this, "session");
        mSeesion = session;
    }

    public static Context getContext(){
        return mBaseApp.getApplicationContext();
    }

    public static Resources getRes(){
        return getContext().getResources();
    }

    public static BaseApp getApplication(){
        return mBaseApp;
    }

}
