package com.purewhite.ywc.purewhite.config;

import android.util.Log;

public final class LogUtils {

    private static boolean okhttp=true;

    public static void okHttp(String msg)
    {
        if (okhttp)
        {
            Log.d("Okhttp",msg);
        }
    }



}
