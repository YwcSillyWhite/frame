package com.purewhite.ywc.purewhite.network.retrofit.request.http;


import com.purewhite.ywc.purewhite.network.retrofit.base.BaseRetrofit;
import com.purewhite.ywc.purewhite.network.rxjava.HttpObserver;
import com.purewhite.ywc.purewhite.network.rxjava.RxSchedulers;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @author yuwenchao
 */ //第3方封装利于后期的修改第3方数据
public class HttpUtils2 {

    private final HttpService httpService;
    public static HttpUtils2 newInstance(String baseUri) {
        return new HttpUtils2(baseUri);
    }

    private HttpUtils2(String baseUri) {
        httpService = BaseRetrofit.newInstance().init(baseUri).create(HttpService.class);
    }




    //例子
    public <T>void getRequest(String url,Map<String,String> maps, HttpObserver<T> httpObserver)
    {
        Observable<T> observable = httpService.get(url,maps);
        observable.compose(RxSchedulers.<T>compose()).subscribe(httpObserver);
    }


    //例子
    public void getRequestBean(String url,Map<String,String> maps, HttpObserver<ResponseBody> httpObserver)
    {
        httpService.getResponBody(url, maps).compose(RxSchedulers.<ResponseBody>compose()).
                subscribe(new HttpObserver<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {

            }
        });
    }







}
