package com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BaseViewHolder;
import com.purewhite.ywc.purewhite.adapter.vlayout.VlayoutType;
import com.purewhite.ywc.purewhite.adapter.vlayout.child.VlayoutBindAdapter;
import com.purewhite.ywc.purewhite.adapter.vlayout.child.VlayoutBindTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class TwoAdapter extends VlayoutBindTypeAdapter {

    private boolean isShow;

    public TwoAdapter() {
        super(new ArrayList());
        addLayout(VlayoutType.coupon_two,R.layout.adapter_fragment_coupon_two);
    }

    public void setShow(boolean isShow) {
        if (this.isShow!=isShow)
        {
            this.isShow=isShow;
            notifyDataSetChanged();
        }
    }



    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new  StickyLayoutHelper();
    }

    @Override
    public int getItemViewType(int position) {
        return VlayoutType.coupon_two;
    }

    @Override
    public int getItemCount() {
        return isShow?1:0;
    }

    @Override
    protected void onData(BaseViewHolder holder, int position, Object o) {

    }


}
