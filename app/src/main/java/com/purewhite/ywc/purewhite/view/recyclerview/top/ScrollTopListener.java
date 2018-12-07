package com.purewhite.ywc.purewhite.view.recyclerview.top;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.purewhite.ywc.purewhite.view.recyclerview.onscroll.LoadOnScrollListener;

/**
 * 置顶滑动监听
 * @author yuwenchao
 */
public class ScrollTopListener extends LoadOnScrollListener {

    private ScrollTopHelp scrollTopHelp;
    //item在什么时候显示,默认值是20
    private int position=20;

    public ScrollTopListener(ScrollTopHelp scrollTopHelp) {
        this.scrollTopHelp = scrollTopHelp;
    }

    public ScrollTopListener(ScrollTopHelp scrollTopHelp, int position) {
        this.scrollTopHelp = scrollTopHelp;
        this.position=position;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        //linearlayoutManager包括gridlayoutmanager
        if (layoutManager instanceof LinearLayoutManager)
        {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            if (firstVisibleItemPosition>=position)
            {
                scrollTopHelp.moveIn();
            }
            else
            {
                scrollTopHelp.moveOut();
            }
        }
    }
}
