package com.purewhite.ywc.purewhite.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.purewhite.ywc.purewhite.mvp.activity.BaseActivity;
import com.purewhite.ywc.purewhite.network.rxjava.RxDisposableManager;

import java.util.Stack;

/**
 *
 * @author yuwenchao
 * @date 2018/11/13
 */

public final class  AppUtils {

    public AppUtils() {
        throw new UnsupportedOperationException("you can not create object");
    }

    public static BaseAppUtils getApplication() {
        return BaseAppUtils.getBaseAppUtils();
    }

    private static Stack<BaseActivity> stack=new Stack<>();
    static Application.ActivityLifecycleCallbacks activityLifecycleCallbacks=new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            if (activity instanceof BaseActivity) {
                stack.add(((BaseActivity) activity));
            }
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            stack.remove(activity);
            if (stack.isEmpty())
            {
                RxDisposableManager.getInstance().clear();
            }

        }
    };


    //初始化
    public static void init() {
        getApplication().registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }


    public static Activity obtainTopActivity()
    {
        if (!stack.isEmpty())
        {
            return stack.lastElement();
        }
        return null;
    }

    //判断activity是否存在这个activity
    public static void removeActivity(String id)
    {
        if (id==null||id.isEmpty())
            return;
        for(BaseActivity baseActivity:stack)
        {
            if (baseActivity.getActivityId()!=null&&!baseActivity.getActivityId().isEmpty()
                    &&baseActivity.getActivityId().equals(id))
            {
                //在activity的生命周期监听谢了删除stack里面的activity，所以这边不用处理
                baseActivity.finish();
                break;
            }
        }
    }

    public static Context getContext()
    {
        if (AppUtils.obtainTopActivity()!=null) {
            return AppUtils.obtainTopActivity();
        }
        return getApplication();
    }

}
