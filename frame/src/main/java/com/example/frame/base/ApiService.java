package com.example.frame.base;

import com.example.bean.TestInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 刘博 on 2020/6/22
 */
public interface ApiService {

    //测试
    @GET("study/public/index.php/verify")
    Observable<TestInfo> getVerify();

}
