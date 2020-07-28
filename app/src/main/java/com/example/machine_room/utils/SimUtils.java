package com.example.machine_room.utils;

import android.telephony.TelephonyManager;

import com.example.frame.base.BaseApp;

/**
 * Created by 刘博 on 2020/7/17
 */
public class SimUtils {

    /**
     * @throws ()，有SIM卡则调用付费SDK
     * @author CX-
     * @判断 是否含有sim卡
     **/
    public static boolean readSIMCard() {

        TelephonyManager telManager = (TelephonyManager)
                BaseApp.getContext().getSystemService(BaseApp.getContext().TELEPHONY_SERVICE);
        String operator = telManager.getSimOperator();
        if (operator != null) {
            if (operator.equals("46000") || operator.equals("46002") || operator.equals("46007")) {
                //中国移动
                return true;
            } else if (operator.equals("46001")) {
                //中国联通
                return true;
            } else if (operator.equals("46003")) {
                //中国电信
                return true;
            }
        } else return false;
        return false;
    }
}
