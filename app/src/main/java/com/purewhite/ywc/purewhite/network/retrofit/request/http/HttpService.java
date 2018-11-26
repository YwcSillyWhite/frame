package com.purewhite.ywc.purewhite.network.retrofit.request.http;



import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * @author yuwenchao
 */
public interface HttpService {


    @GET("")
    Observable<ResponseBody> request();
}
