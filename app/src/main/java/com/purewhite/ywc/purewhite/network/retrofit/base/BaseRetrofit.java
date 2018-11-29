package com.purewhite.ywc.purewhite.network.retrofit.base;

import com.purewhite.ywc.purewhite.config.LogUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author yuwenchao
 * @date 2018/11/20
 */

public class BaseRetrofit {

    private final String baseUri="https://hzcangyu.com/";
    private Retrofit retrofit;
    private static BaseRetrofit basRetrofit;
    public static BaseRetrofit newInstance() {
        if (basRetrofit==null)
        {
            synchronized (BaseRetrofit.class)
            {
                if (basRetrofit==null)
                {
                    basRetrofit=new BaseRetrofit();
                }
            }
        }
        return basRetrofit;
    }


    //初始化
    public BaseRetrofit init()
    {
        if (retrofit==null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUri)
                    .client(getOkHttp())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return this;
    }

    //初始化
    public Retrofit init(String baseUri)
    {
        return new Retrofit.Builder()
                .baseUrl(baseUri)
                .client(getOkHttp())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    /**
     * 设置拦截器
     * @return okhttpclient
     */
    private OkHttpClient getOkHttp()
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.okHttp(message);
            }
        });
        /**
         * NONE  :  没有log
         * BASEIC:  请求/响应行
         * HEADER:  请求/响应行 + 头
         * BODY  :  请求/响应航 + 头 + 体
         */
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLoggingInterceptor);
        return builder.build();
    }



    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

}
