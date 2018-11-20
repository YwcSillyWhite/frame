package com.purewhite.ywc.purewhite.retrofit.base;

/**
 * Created by yuwenchao on 2018/11/20.
 */

public class HttpUtils extends BaseRetrofit{

    private static HttpUtils httpUtils;
    public static HttpUtils newInstance() {
        if (httpUtils==null)
        {
            synchronized (HttpUtils.class)
            {
                if (httpUtils==null)
                {
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }

    private HttpUtils() {
        init();
    }

}
