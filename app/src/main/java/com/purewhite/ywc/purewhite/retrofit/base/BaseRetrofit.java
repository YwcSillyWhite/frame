package com.purewhite.ywc.purewhite.retrofit.base;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yuwenchao on 2018/11/20.
 */

public class BaseRetrofit {

    private final String baseUri="";
    private Retrofit retrofit;
    //初始化
    protected void init()
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUri)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

}
