package com.purewhite.ywc.purewhite.adapter.recyclerview.loadview;

import android.view.View;

import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BaseViewHolder;

/**
 * Created by yuwenchao on 2018/11/19.
 */

public abstract class LoadView {
    private int state;
    //加载更多
    public static final int STATE_LOAD=1;
    //加载失败
    public static final int STATE_FAIL=2;
    //加载结束，下次还可以进行加载
    public static final int STATE_FINISH_TRUE=3;
    //加载结束或者开始状态，下次不能进行加载
    public static final int STATE_FINISH_FALSE=4;
    //加载结束，没有更多数据的时候
    public static final int STATE_FINISH_NODATA=5;



    public abstract  int getLayoutId();

    abstract int getLoadViewId();

    abstract int getFailViewId();

    abstract int getNoMoreViewId();


    public void onBindView(BaseViewHolder holder)
    {
        switch (state)
        {
            case STATE_LOAD:
                visible(holder,getLoadViewId(), View.VISIBLE);
                visible(holder,getFailViewId(), View.GONE);
                visible(holder,getNoMoreViewId(), View.GONE);
                break;
            case STATE_FAIL:
                visible(holder,getLoadViewId(), View.GONE);
                visible(holder,getFailViewId(), View.VISIBLE);
                visible(holder,getNoMoreViewId(), View.GONE);
                break;
            case STATE_FINISH_NODATA:
                visible(holder,getLoadViewId(), View.GONE);
                visible(holder,getFailViewId(), View.GONE);
                visible(holder,getNoMoreViewId(), View.VISIBLE);
                break;
            case STATE_FINISH_TRUE:
            case STATE_FINISH_FALSE:
                visible(holder,getLoadViewId(), View.GONE);
                visible(holder,getFailViewId(), View.GONE);
                visible(holder,getNoMoreViewId(), View.GONE);
                break;
                default:
                    visible(holder,getLoadViewId(), View.GONE);
                    visible(holder,getFailViewId(), View.GONE);
                    visible(holder,getNoMoreViewId(), View.GONE);
                    break;
        }
    }


    private void visible(BaseViewHolder holder,int id,int visible) {
        holder.setVisibility(id, visible);
    }

    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }

}
