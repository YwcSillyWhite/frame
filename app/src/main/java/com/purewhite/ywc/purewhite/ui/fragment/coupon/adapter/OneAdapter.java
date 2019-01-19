package com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhite.adapter.vlayout.VlayoutType;
import com.purewhite.ywc.purewhite.adapter.vlayout.child.VlayoutBindTypeAdapter;
import com.purewhite.ywc.purewhite.bean.main.MainBean;
import com.purewhite.ywc.purewhite.databinding.AdapterFragmentCouponOneBinding;
import com.purewhite.ywc.purewhite.network.imageload.ImageLoader;

import java.util.ArrayList;

public class OneAdapter extends VlayoutBindTypeAdapter<MainBean.DataBean> {


    public OneAdapter() {
        super(new ArrayList<MainBean.DataBean>());
        addLayout(VlayoutType.coupon_one,R.layout.adapter_fragment_coupon_one);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new GridLayoutHelper(5);
    }

    @Override
    protected void onData(BindHolder holder, int position, MainBean.DataBean dataBean) {
        AdapterFragmentCouponOneBinding viewDataBinding = (AdapterFragmentCouponOneBinding) holder.getViewDataBinding();
        ImageLoader.newInstance().initCircle(viewDataBinding.couponImage,dataBean.getItem_pic());
    }

    @Override
    public int getItemViewType(int position) {
        return VlayoutType.coupon_one;
    }
}
