package com.example.frame.base;

import com.example.frame.constans.ApiConstans;
import com.example.frame.net.NetManger;

public class MainModel implements IMianModel {

    NetManger mNetManger = NetManger.getInstance();

    @Override
    public void getData(IMainPersenter pPresenter, int whichApi, int dataType, Object[] t) {
        switch (whichApi){
            case ApiConstans.TEST_API:
                mNetManger.netWork(mNetManger.getService().getVerify(),pPresenter,whichApi,dataType);
                break;
        }
    }
}
