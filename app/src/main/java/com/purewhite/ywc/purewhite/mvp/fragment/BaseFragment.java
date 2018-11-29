package com.purewhite.ywc.purewhite.mvp.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 * @author yuwenchao
 * @date 2018/11/14
 */

public abstract class BaseFragment<DB extends ViewDataBinding> extends Fragment{

    protected DB mDataBinding;
    //当前fragment是否显示
    protected boolean fragmentShow;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (getLayout()!=0)
        {
            mDataBinding=DataBindingUtil.inflate(inflater,getLayout(),container,false);
            View view = mDataBinding.getRoot();
            return view;
        }
        else {
            return null;
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    //布局
    protected abstract int getLayout();
    //初始化布局
    protected abstract void initView();


    //当前fragment是否显示
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
        {
            fragmentShow=true;
        }
        else
        {
            if (fragmentShow)
            {
                fragmentShow=false;
            }
        }
    }
}
