package com.purewhite.ywc.purewhite.adapter.recyclerview.fullview;

import android.view.View;

import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BaseViewHolder;

/**
 * 没有网络，没有数据，第一次加载
 * @author yuwenchao
 */
public abstract class FullView {

    private int fullState;
    //加载更多
    public static final int FULL_LOAD=1;
    //没有网络
    public static final int FULL_NETWORK=2;
    //没有数据
    public static final int FULL_DATA=3;
    //不显示
    public static final int FULL_REST=0;

    public abstract  int getLayoutId();

    abstract int getLoadId();

    abstract int getNetworkId();

    abstract int getDataId();


    public void onBindView(BaseViewHolder holder)
    {
        switch (fullState)
        {
            case FULL_DATA:
                visible(holder,getDataId(), View.VISIBLE);
                visible(holder,getNetworkId(), View.GONE);
                visible(holder,getLoadId(), View.GONE);
                break;
            case FULL_NETWORK:
                visible(holder,getDataId(), View.GONE);
                visible(holder,getNetworkId(), View.VISIBLE);
                visible(holder,getLoadId(), View.GONE);
                break;
            case FULL_LOAD:
                visible(holder,getDataId(), View.GONE);
                visible(holder,getNetworkId(), View.GONE);
                visible(holder,getLoadId(), View.VISIBLE);
                break;
            default:
                visible(holder,getDataId(), View.GONE);
                visible(holder,getNetworkId(), View.GONE);
                visible(holder,getLoadId(), View.GONE);
                break;
        }
    }



    private void visible(BaseViewHolder holder,int id,int visible) {
        holder.setVisibility(id, visible);
    }


    public boolean isShow()
    {
        if (fullState==FULL_DATA||fullState==FULL_LOAD||fullState==FULL_NETWORK)
            return true;
        return false;
    }

    public void setFullState(int fullState) {
        this.fullState = fullState;
    }
}
