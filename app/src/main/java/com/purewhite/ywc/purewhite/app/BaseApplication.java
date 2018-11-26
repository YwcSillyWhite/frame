package com.purewhite.ywc.purewhite.app;

import android.app.Application;
//import android.support.multidex.MultiDex;

/**
 * Created by yuwenchao on 2018/11/3.
 */

public class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);

    }
}
