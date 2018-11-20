package com.purewhite.ywc.purewhite.retrofit.io;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yuwenchao on 2018/11/20.
 */

public interface NewService {

    @GET("")
    Call<ResponseBody> getMessage();
}
