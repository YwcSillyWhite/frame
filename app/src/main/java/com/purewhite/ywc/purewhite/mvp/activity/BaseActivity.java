package com.purewhite.ywc.purewhite.mvp.activity;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yuwenchao on 2018/11/3.
 */

public abstract class BaseActivity<DB extends ViewDataBinding> extends AppCompatActivity{

    //是否横屏
    protected boolean isPortait=true;
    protected DB mDataBinding;
    //确宝同一个物品的页面唯一；
    private String activityId;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeView();
        //设置横竖平
        try
        {
            setRequestedOrientation(isPortait? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT:
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        catch (Exception e)
        {

        }
        //DataBinding绑定
        mDataBinding = DataBindingUtil.setContentView(this, getLayout());
        initView();
    }

    protected void beforeView() {

    }

    //布局id
    protected abstract int getLayout();
    //初始化布局
    protected abstract void initView();

}
