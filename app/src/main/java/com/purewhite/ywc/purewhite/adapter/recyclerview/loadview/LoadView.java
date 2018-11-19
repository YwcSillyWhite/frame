package com.purewhite.ywc.purewhite.adapter.recyclerview.loadview;

import android.view.View;

import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BaseViewHolder;

/**
 * Created by yuwenchao on 2018/11/19.
 */

public abstract class LoadView {
    private int state;
    //是否能加载
    private boolean isCanLoad;

    //不显示
    public static final int STATE_NOSHOW=0;
    //加载更多
    public static final int STATE_LOAD=1;
    //加载失败
    public static final int STATE_FAIL=2;
    //没有数据
    public static final int STATE_NOMOR=3;
    //完成
    public static final int STATE_FINISH=4;


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
            case STATE_NOMOR:
                visible(holder,getLoadViewId(), View.GONE);
                visible(holder,getFailViewId(), View.GONE);
                visible(holder,getNoMoreViewId(), View.VISIBLE);
                break;
            case STATE_FINISH:
            case STATE_NOSHOW:
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
    public boolean isCanLoad() {
        return isCanLoad;
    }

    public void setCanLoad(boolean canLoad) {
        isCanLoad = canLoad;
    }


}
