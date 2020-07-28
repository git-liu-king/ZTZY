package com.example.frame.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;

/**
 * Created by 刘博 on 2020/6/17
 */
public class LibUtils {
    /**
     * 获取状态栏高度
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    //设置布局距离状态栏高度
    public static void setLayoutPadding(Activity activity, View contentLayout) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            contentLayout
                    .setPadding(contentLayout.getPaddingLeft(), getStatusBarHeight(activity) + contentLayout.getPaddingTop(),
                            contentLayout.getPaddingRight(), contentLayout.getPaddingBottom());
        }

    }

}
