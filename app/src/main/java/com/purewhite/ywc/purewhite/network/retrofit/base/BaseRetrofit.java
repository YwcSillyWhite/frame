package com.purewhite.ywc.purewhite.network.retrofit.base;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author yuwenchao
 * @date 2018/11/20
 */

public class BaseRetrofit {

    private final String baseUri="";
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
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUri)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return this;
    }




    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

}
