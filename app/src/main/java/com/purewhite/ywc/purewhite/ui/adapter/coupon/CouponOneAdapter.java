package com.purewhite.ywc.purewhite.ui.adapter.coupon;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.viewholder.BaseViewHolder;
import com.purewhite.ywc.purewhite.adapter.vlayout.VlayoutType;
import com.purewhite.ywc.purewhite.adapter.vlayout.child.VlayoutBindAdapter;


public class CouponOneAdapter extends VlayoutBindAdapter{

    private boolean isShow;
    public void flushShow(boolean isShow) {
        if (this.isShow!=isShow)
        {
            this.isShow=isShow;
            mData.clear();
            if (isShow)
            {
                mData.add("");
            }
            notifyDataSetChanged();
        }
    }

    public CouponOneAdapter() {
        addLayout(VlayoutType.coupon_one, R.layout.adapter_coupon_one);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new SingleLayoutHelper();
    }


    @Override
    protected int getDataType(int position) {
        return VlayoutType.coupon_one;
    }

    @Override
    public int getItemCount() {
        return isShow?1:0;
    }

    @Override
    protected void onData(BaseViewHolder holder, int position, Object o) {

    }

}
