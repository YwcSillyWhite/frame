package com.purewhite.ywc.purewhite.network.retrofit.request.http;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
/**
 * @author yuwenchao
 */
public interface HttpService {

    @GET()
    <T>Observable<T> get(@Url String url, @QueryMap Map<String,String> maps);
}
