package com.purewhite.ywc.purewhite.app;

import android.app.Application;

/**
 *
 * @author yuwenchao
 * @date 2018/11/3
 */

public class BaseAppUtils extends Application{

    private static BaseAppUtils baseAppUtils;

    public static BaseAppUtils getBaseAppUtils() {
        return baseAppUtils;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        baseAppUtils=this;
        AppUtils.init();
    }
}
