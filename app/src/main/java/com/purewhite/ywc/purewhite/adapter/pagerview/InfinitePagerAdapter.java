package com.purewhite.ywc.purewhite.adapter.pagerview;

import android.view.View;

public class InfinitePagerAdapter<T> extends BasePagerAdapter<T> {
    @Override
    protected View obtainView(int position) {
        return null;
    }


    //长度设置为最大值
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
}
