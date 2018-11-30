package com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BaseViewHolder;
import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BindHolder;
import com.purewhite.ywc.purewhite.adapter.vlayout.VlayoutType;
import com.purewhite.ywc.purewhite.adapter.vlayout.child.VlayoutBindAdapter;
import com.purewhite.ywc.purewhite.adapter.vlayout.child.VlayoutBindTypeAdapter;
import com.purewhite.ywc.purewhite.bean.main.MainBean;
import com.purewhite.ywc.purewhite.databinding.AdapterFragmentCouponOneBinding;
import com.purewhite.ywc.purewhite.imageload.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class TwoAdapter extends VlayoutBindAdapter {

    private boolean isShow;

    public void setShow(boolean isShow) {
        if (this.isShow!=isShow)
        {
            this.isShow=isShow;
            notifyDataSetChanged();
        }
    }

    public TwoAdapter() {
        super(null,R.layout.adapter_fragment_coupon_two);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new SingleLayoutHelper();
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
