package com.example.frame.net;

import com.example.frame.base.ApiService;
import com.example.frame.base.BaseObserver;
import com.example.frame.base.IMainPersenter;
import com.example.frame.constans.NetConstans;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetManger {

    private volatile static NetManger sNetUtils;

    private NetManger() {
    }

    public static NetManger getInstance() {
        if (sNetUtils == null) {
            synchronized (NetManger.class) {
                sNetUtils = new NetManger();
            }
        }
        return sNetUtils;
    }

    //方法里面的参数是可变参数,意思就是可传可不传
    public <T> ApiService getService(T... t) {
        String baseUrl = NetConstans.TEST_BASE;
        if (t != null && t.length != 0) {
            baseUrl = (String) t[0];
        }

        ApiService localService = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(NetInterceptor.getNetInterceptor().getClientWithoutCache())
                .build().create(ApiService.class);
        return localService;
    }

    public <T> void netWork(Observable<T> observable, final IMainPersenter presenter, final int whichApi, final int dataType) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(Object value) {
                        presenter.onSuccess(whichApi, dataType, value);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        presenter.onFail(whichApi, e.getMessage());
                    }
                });
    }

}
