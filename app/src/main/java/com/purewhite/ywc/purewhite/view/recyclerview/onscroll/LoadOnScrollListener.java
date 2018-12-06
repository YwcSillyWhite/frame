package com.purewhite.ywc.purewhite.view.recyclerview.onscroll;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.purewhite.ywc.purewhite.imageload.ImageLoader;

/**
 * 滑动不加载图片
 * @author yuwenchao
 *
 */
public class LoadOnScrollListener extends RecyclerView.OnScrollListener {


    /**
     *
     * @param recyclerView
     * @param newState  滚动状态
     *  SCROLL_STATE_IDLE=0  （静止没有滚动）
     *  SCROLL_STATE_DRAGGING=1    （正在被外部拖拽,一般为用户正在用手指滚动）
     *  SCROLL_STATE_SETTLING=2      （自动滚动）
     */
    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState==RecyclerView.SCROLL_STATE_IDLE)
        {
            ImageLoader.newInstance().start(recyclerView.getContext());
        }
        else
        {
            ImageLoader.newInstance().stop(recyclerView.getContext());
        }
    }
}
