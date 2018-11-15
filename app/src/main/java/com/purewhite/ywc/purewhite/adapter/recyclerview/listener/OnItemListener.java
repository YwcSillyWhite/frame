package com.purewhite.ywc.purewhite.adapter.recyclerview.listener;

import android.view.View;

import com.purewhite.ywc.purewhite.adapter.recyclerview.BaseAdapter;


/**
 * Created by yuwenchao on 2018/11/15.
 * 子类点击事件
 */

public interface OnItemListener {
    void OnItemCall(BaseAdapter adapter, View view, int position);
}
