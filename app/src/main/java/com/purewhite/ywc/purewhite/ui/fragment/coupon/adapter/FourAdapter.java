package com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.viewholder.BaseViewHolder;
import com.purewhite.ywc.purewhite.adapter.vlayout.VlayoutType;
import com.purewhite.ywc.purewhite.adapter.vlayout.child.VlayoutBindTypeAdapter;

import java.util.ArrayList;

public class FourAdapter extends VlayoutBindTypeAdapter {

    private boolean isShow;

    public FourAdapter() {
        super(new ArrayList());
        addLayout(VlayoutType.coupon_four,R.layout.adapter_fragment_coupon_four);
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
        return VlayoutType.coupon_four;
    }

    @Override
    public int getItemCount() {
        return isShow?1:0;
    }

    @Override
    protected void onData(BaseViewHolder holder, int position, Object o) {

    }


}
