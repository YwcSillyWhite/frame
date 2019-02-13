package com.purewhite.ywc.purewhite.ui.adapter.coupon;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhite.adapter.vlayout.VlayoutType;
import com.purewhite.ywc.purewhite.adapter.vlayout.child.VlayoutBindAdapter;
import com.purewhite.ywc.purewhite.bean.GoodsListBean;
import com.purewhite.ywc.purewhite.databinding.AdapterCouponTwoBinding;
import com.purewhite.ywc.purewhite.network.imageload.ImageLoader;


public class CouponTwoAdapter extends VlayoutBindAdapter<GoodsListBean> {

    public CouponTwoAdapter() {
        addLayout(VlayoutType.coupon_two, R.layout.adapter_coupon_two);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new GridLayoutHelper(5);
    }

    @Override
    protected int getDataType(int position) {
        return VlayoutType.coupon_two;
    }

    @Override
    protected void onData(BindHolder holder, int position, GoodsListBean goodsListBean) {
        if (holder.getBinding() instanceof AdapterCouponTwoBinding)
        {
            initTwo((AdapterCouponTwoBinding)holder.getBinding(),position,goodsListBean);
        }
    }

    private void initTwo(AdapterCouponTwoBinding binding, int position, GoodsListBean goodsListBean) {
        ImageLoader.newInstance().initCircle(binding.goodsImg,goodsListBean.getItempic());
        binding.goodsTitle.setText(goodsListBean.getItemtitle());
    }
}
