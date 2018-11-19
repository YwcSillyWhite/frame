package com.purewhite.ywc.purewhite.adapter.recyclerview.loadview;

import com.purewhite.ywc.purewhite.R;

/**
 * Created by yuwenchao on 2018/11/19.
 */

public class LoadViewImp extends LoadView{

    @Override
    public int getLayoutId() {
        return R.layout.loadview;
    }

    @Override
    int getLoadViewId() {
        return R.id.load;
    }

    @Override
    int getFailViewId() {
        return R.id.fail;
    }

    @Override
    int getNoMoreViewId() {
        return R.id.noMore;
    }


}
