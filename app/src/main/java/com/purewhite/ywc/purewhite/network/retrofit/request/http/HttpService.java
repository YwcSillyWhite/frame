package com.purewhite.ywc.purewhite.network.retrofit.request.http;

import com.purewhite.ywc.purewhite.bean.base.ResponseBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
/**
 * @author yuwenchao
 * 需要什么根据自己需求变化
 */
public interface HttpService {

    @GET()
    <T> Observable<T> get(@Url String url, @QueryMap Map<String,String> maps);


    @GET()
    Observable<ResponseBody> getResponBody(@Url String url, @QueryMap Map<String,String> maps);
}
