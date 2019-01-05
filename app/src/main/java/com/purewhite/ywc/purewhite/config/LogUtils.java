package com.purewhite.ywc.purewhite.config;

import android.util.Log;

public final class LogUtils {

    private static boolean okhttp=true;
    private static boolean error=true;


    //请求数据参数
    public static void okHttp(String msg)
    {
        if (okhttp)
        {
            Log.d("Okhttp",msg);
        }
    }

    //错误类型
    public static void error(String msg)
    {
        if (error)
        {
            Log.d("error",msg);
        }
    }



}
