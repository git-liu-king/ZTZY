package com.example.machine_room.base;

import com.example.frame.base.BaseApp;
import com.igexin.sdk.PushManager;

/**
 * Created by 刘博 on 2020/7/17
 */
public class MainApplication extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        PushManager.getInstance().initialize(this);
    }
}
