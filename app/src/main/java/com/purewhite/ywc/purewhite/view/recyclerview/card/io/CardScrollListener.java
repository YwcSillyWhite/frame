package com.purewhite.ywc.purewhite.view.recyclerview.card.io;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author yuwenchao
 */
public interface CardScrollListener {
    int LEFT=0;
    int RIGHT=1;
    void call(RecyclerView.ViewHolder viewHolder,int type);

    void scrollChange(RecyclerView.ViewHolder viewHolder, float sacle);
}
