package com.example.frame.base;

/**
 * Created by 刘博 on 2020/6/11
 */
public interface IMainPersenter<P> extends IMainView {

    //该方法用于V层向P层回调数据
    void getData(int whichApi, int dataType, P... mPS);

}
