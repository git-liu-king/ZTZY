package com.example.frame.base;

/**
 * Created by 刘博 on 2020/6/11
 */
public interface IMainView<T> {

    //请求成功
    void onSuccess(int whichApi, int datatype, T... t);
    //请求失败
    void onFail(int whichApi, String msg);

}
