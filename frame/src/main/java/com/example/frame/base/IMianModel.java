package com.example.frame.base;

/**
 * Created by 刘博 on 2020/6/11
 */
public interface IMianModel<T> {

    //该方法接收P层回调的数据
    void getData(IMainPersenter pPresenter, int whichApi, int dataType, T... t);

}
