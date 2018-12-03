package com.purewhite.ywc.purewhite.adapter.recyclerview.io;

import android.support.v7.widget.RecyclerView;
import android.view.View;



/**
 *
 * @author yuwenchao
 * @date 2018/11/15
 * 子类点击事件
 */

public interface OnItemListener {
    void OnItemCall(RecyclerView.Adapter adapter, View view, int position);
}
