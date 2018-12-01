package com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BindHolder;
import com.purewhite.ywc.purewhite.adapter.vlayout.VlayoutType;
import com.purewhite.ywc.purewhite.adapter.vlayout.child.VlayoutBindTypeAdapter;
import com.purewhite.ywc.purewhite.bean.main.MainBean;
import com.purewhite.ywc.purewhite.databinding.AdapterFragmentCouponThreeBinding;
import com.purewhite.ywc.purewhite.imageload.ImageLoader;

import java.util.ArrayList;

public class ThreeAdapter extends VlayoutBindTypeAdapter<MainBean.DataBean> {

    public ThreeAdapter() {
        super(new ArrayList<MainBean.DataBean>());
        addLayout(VlayoutType.coupon_three,R.layout.adapter_fragment_coupon_three);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new GridLayoutHelper(1);
    }

    @Override
    protected void onData(BindHolder holder, int position, MainBean.DataBean dataBean) {
        AdapterFragmentCouponThreeBinding viewDataBinding = (AdapterFragmentCouponThreeBinding) holder.getViewDataBinding();
        viewDataBinding.couponText.setText(dataBean.getItem_title());
        ImageLoader.newInstance().init(viewDataBinding.couponImg,dataBean.getItem_pic());
    }

    @Override
    public int getItemViewType(int position) {
        return VlayoutType.coupon_three;
    }
}
