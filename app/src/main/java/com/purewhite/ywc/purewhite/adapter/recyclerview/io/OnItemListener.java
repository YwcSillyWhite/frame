package com.purewhite.ywc.purewhite.adapter.recyclerview.io;

import android.support.v7.widget.RecyclerView;
import android.view.View;



/**
 * Created by yuwenchao on 2018/11/15.
 * 子类点击事件
 */

public interface OnItemListener<T> {
    void OnItemCall(RecyclerView.Adapter adapter, View view, int position, Object t);
}
