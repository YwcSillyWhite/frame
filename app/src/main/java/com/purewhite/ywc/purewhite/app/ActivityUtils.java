package com.purewhite.ywc.purewhite.app;

import android.content.Intent;

/**
 *
 * @author yuwenchao
 * @date 2018/11/13
 */
public class ActivityUtils {
    private static ActivityUtils activityUtils;
    public static ActivityUtils newInstance() {
        if (activityUtils==null)
        {
            synchronized (ActivityUtils.class)
            {
                if (activityUtils==null)
                {
                    activityUtils=new ActivityUtils();
                }
            }
        }
        return activityUtils;
    }

    public void startActivity(Intent intent)
    {
        AppUtils.getContext().startActivity(intent);
    }

    public void startActivity(Class<?> cls)
    {
        Intent intent = new Intent(AppUtils.getContext(),cls);
        AppUtils.getContext().startActivity(intent);
    }

    //跳转唯一商品activity
    public void startSoleActivity(Intent intent,String id)
    {
        AppUtils.removeActivity(id);
        AppUtils.getContext().startActivity(intent);
    }
}
