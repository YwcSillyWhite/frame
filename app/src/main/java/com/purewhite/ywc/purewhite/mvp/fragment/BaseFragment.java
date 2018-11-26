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

    private DB mDataBind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mDataBind=DataBindingUtil.inflate(inflater,getLayout(),container,false);
        View view = mDataBind.getRoot();
        return view;
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
}
