package com.purewhite.ywc.purewhite.adapter.recyclerview.io;

import android.support.v7.widget.RecyclerView;
import android.view.View;
/**
 *
 * @author yuwenchao
 * @date 2018/11/15
 * recycler 数据类型点击事件
 */

public interface OnDataListener {
    void OnItemCall(RecyclerView.Adapter adapter, View view, int position);
}
