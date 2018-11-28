package com.purewhite.ywc.purewhite.network.retrofit.request.http;


import com.purewhite.ywc.purewhite.network.retrofit.base.BaseRetrofit;
import com.purewhite.ywc.purewhite.network.rxjava.HttpObserver;
import com.purewhite.ywc.purewhite.network.rxjava.RxSchedulers;

import java.util.Map;

import okhttp3.ResponseBody;

/**
 * @author yuwenchao
 */ //第3方封装利于后期的修改第3方数据
public class HttpUtils {

    private static HttpUtils httpUtils;
    private final HttpService httpService;
    public static HttpUtils newInstance() {
        if (httpUtils==null)
        {
            synchronized (HttpUtils.class)
            {
                if (httpUtils==null)
                {
                    httpUtils=new HttpUtils();
                }
            }
        }
        return httpUtils;
    }


    private HttpUtils() {
        httpService = BaseRetrofit.newInstance().init().create(HttpService.class);
    }


    //例子
    public <T>void getRequest(String Url,Map<String,String> maps,HttpObserver<T> httpObserver)
    {
        httpService.<T>get(Url,maps).compose(RxSchedulers.<T>compose()).subscribe(httpObserver);
    }

}
