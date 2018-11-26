package com.purewhite.ywc.purewhite.ptr.io;

import android.view.View;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * @author yuwenchao
 */
public abstract class PtrCallBack extends PtrDefaultHandler {
    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        onPullDown();
    }

    //下啦刷新
    public abstract void onPullDown();


    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return checkCanPullDown()&&super.checkCanDoRefresh(frame, content, header);
    }

    //判断能否下啦刷新
    public boolean checkCanPullDown()
    {
        return true;
    }
}
