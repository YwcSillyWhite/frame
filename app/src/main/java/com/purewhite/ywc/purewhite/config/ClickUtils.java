package com.purewhite.ywc.purewhite.config;

import android.view.View;

/**
 * Created by yuwenchao on 2018/11/5.
 * 防多次点击的
 */

public final class ClickUtils {

    public static boolean  clickable(View view)
    {
        Object tag = view.getTag(-1);
        long oldTime=tag!=null? ((long) tag):0;
        long newTime = System.currentTimeMillis();
        if (newTime-oldTime>=600)
        {
            view.setTag(-1,newTime);
            return true;
        }
        return false;
    }
}
