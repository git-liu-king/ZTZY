package com.example.machine_room.utils;

import android.annotation.TargetApi;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.frame.base.BaseApp;

import java.lang.reflect.Method;

/**
 * Created by 刘博 on 2020/7/24
 */
public class CameraUtil {

    private static final String TAG = "CameraUtil";
    public static Camera mCamera;

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void changeFlashLight(boolean openOrClose) {
        //判断API是否大于24（安卓7.0系统对应的API）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Toast.makeText(BaseApp.getContext(), "aaaa", Toast.LENGTH_SHORT).show();
            try {
                //获取CameraManager
                CameraManager mCameraManager = (CameraManager) BaseApp.getContext().getSystemService(BaseApp.getContext().CAMERA_SERVICE);
                //获取当前手机所有摄像头设备ID
                String[] ids = mCameraManager.getCameraIdList();
                for (String id : ids) {
                    CameraCharacteristics c = mCameraManager.getCameraCharacteristics(id);
                    //查询该摄像头组件是否包含闪光灯
                    Boolean flashAvailable = c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                    Integer lensFacing = c.get(CameraCharacteristics.LENS_FACING);
                    if (flashAvailable != null && flashAvailable && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                        //打开或关闭手电筒
                        mCameraManager.setTorchMode(id, openOrClose);
                    }
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(BaseApp.getContext(), "bbbb", Toast.LENGTH_SHORT).show();
            mCamera = Camera.open();
            Camera.Parameters parameters = mCamera.getParameters();
            if (openOrClose) {
                //开启
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                mCamera.setParameters(parameters);
            } else {
                //关闭
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                mCamera.setParameters(parameters);
            }

        }
    }
}
