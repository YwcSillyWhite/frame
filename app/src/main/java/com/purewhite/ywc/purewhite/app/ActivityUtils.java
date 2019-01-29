package com.purewhite.ywc.purewhite.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

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



    public void startActivity(Class<?> cls)
    {
        startActivity(cls,null);
    }


    public void startActivity(Intent intent)
    {
        AppUtils.getContext().startActivity(intent);
    }

    public void startActivity(Class<?> cls, Bundle bundle)
    {
        if (cls==null) {
            return;
        }
        Intent intent=new Intent(AppUtils.getContext(),cls);
        if (bundle!=null) {
            intent.putExtras(bundle);
        }
        AppUtils.getContext().startActivity(intent);
    }

    //跳转唯一商品activity
    public void startSoleActivity(Intent intent,String id)
    {
        AppUtils.removeActivity(id);
        AppUtils.getContext().startActivity(intent);
    }

    public void finish()
    {
        Activity activity = AppUtils.obtainTopActivity();
        if (activity!=null)
        {
            finish(activity);
        }
    }

    public void finish(Activity activity)
    {
        finish(activity,null);
    }

    public void finish(Activity activity,Integer requestCode)
    {
        finish(activity,requestCode,null);
    }

    public void finish(Activity activity,Integer requestCode,Bundle bundle)
    {
        if (requestCode!=null)
        {
            Intent intent = new Intent();
            if (bundle!=null)
            {
                intent.putExtras(bundle);
            }
            activity.setResult(requestCode,intent);
        }

        activity.finish();
    }
}
