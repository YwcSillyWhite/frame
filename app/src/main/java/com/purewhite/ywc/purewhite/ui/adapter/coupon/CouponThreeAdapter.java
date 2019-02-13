package com.purewhite.ywc.purewhite.ui.adapter.coupon;


import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.viewholder.BaseViewHolder;
import com.purewhite.ywc.purewhite.adapter.vlayout.VlayoutType;
import com.purewhite.ywc.purewhite.adapter.vlayout.child.VlayoutBindAdapter;


public class CouponThreeAdapter extends VlayoutBindAdapter {

    private boolean isShow;
    public void flushShow(boolean isShow) {
        if (this.isShow!=isShow)
        {
            this.isShow=isShow;
            notifyDataSetChanged();
        }
    }

    public CouponThreeAdapter() {
        addLayout(VlayoutType.coupon_three, R.layout.adapter_coupon_three);
    }


    @Override
    protected int getDataType(int position) {
        return VlayoutType.coupon_three;
    }

    @Override
    protected void onData(BaseViewHolder holder, int position, Object o) {

    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new StickyLayoutHelper();
    }


    @Override
    public int getItemCount() {
        return isShow?1:0;
    }
}
