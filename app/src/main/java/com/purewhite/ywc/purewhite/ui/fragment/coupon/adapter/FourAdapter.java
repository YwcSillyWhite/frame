package com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BindHolder;
import com.purewhite.ywc.purewhite.adapter.vlayout.VlayoutType;
import com.purewhite.ywc.purewhite.adapter.vlayout.child.VlayoutBindTypeAdapter;
import com.purewhite.ywc.purewhite.bean.main.MainBean;
import com.purewhite.ywc.purewhite.config.SizeUtils;
import com.purewhite.ywc.purewhite.databinding.AdapterFragmentCouponOneBinding;
import com.purewhite.ywc.purewhite.databinding.AdapterFragmentCouponThreeBinding;
import com.purewhite.ywc.purewhite.databinding.AdapterMianBinding;
import com.purewhite.ywc.purewhite.imageload.ImageLoader;

import java.util.ArrayList;

public class FourAdapter extends VlayoutBindTypeAdapter<MainBean.DataBean> {


    public FourAdapter() {
        super(new ArrayList<MainBean.DataBean>());
        addLayout(VlayoutType.coupon_four,R.layout.adapter_mian);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new GridLayoutHelper(2);
    }

    @Override
    protected void onData(BindHolder holder, int position, MainBean.DataBean dataBean) {
        AdapterMianBinding viewDataBinding = (AdapterMianBinding) holder.getViewDataBinding();

        View root = viewDataBinding.getRoot();
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) root.getLayoutParams();

        layoutParams.bottomMargin=SizeUtils.dip2px(10);
        if (position%2==0)
        {
            layoutParams.leftMargin=SizeUtils.dip2px(10);
            layoutParams.rightMargin=SizeUtils.dip2px(5);
        }
        else
        {
            layoutParams.rightMargin=SizeUtils.dip2px(10);
            layoutParams.leftMargin=SizeUtils.dip2px(5);
        }

        viewDataBinding.shipTitle.setText(dataBean.getItem_title());
        ImageLoader.newInstance().init(viewDataBinding.shipImg,dataBean.getItem_pic());
    }
}
