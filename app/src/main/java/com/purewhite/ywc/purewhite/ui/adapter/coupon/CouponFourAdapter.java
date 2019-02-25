package com.purewhite.ywc.purewhite.ui.adapter.coupon;

import android.graphics.Paint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhite.adapter.vlayout.VlayoutType;
import com.purewhite.ywc.purewhite.adapter.vlayout.child.VlayoutBindAdapter;
import com.purewhite.ywc.purewhite.bean.GoodsListBean;
import com.purewhite.ywc.purewhite.config.StringUtils;
import com.purewhite.ywc.purewhite.databinding.AdapterGoodsListBinding;
import com.purewhite.ywc.purewhite.network.imageload.ImageLoader;

public class CouponFourAdapter extends VlayoutBindAdapter<GoodsListBean> {

    public CouponFourAdapter() {
        addLayout(VlayoutType.coupon_four, R.layout.adapter_goods_list);
    }


    @Override
    protected int getDataType(int position) {
        return VlayoutType.coupon_four;
    }

    @Override
    protected void onData(BindHolder holder, int position, GoodsListBean goodsListBean) {
        if (holder.getBinding() instanceof AdapterGoodsListBinding)
        {
            initTwo((AdapterGoodsListBinding)holder.getBinding(),position,goodsListBean);
        }
    }

    private void initTwo(AdapterGoodsListBinding binding, int position, GoodsListBean goodsListBean) {
        ImageLoader.newInstance().init(binding.goodsImg,goodsListBean.getItempic());
        binding.goodsTitle.setText(goodsListBean.getItemtitle());
        binding.goodsSola.setText("月销"+StringUtils.obtainSola(goodsListBean.getItemsale()));
        binding.shopName.setText(goodsListBean.getShopname());
        binding.goodsTkMoney.setText(goodsListBean.getTkmoney());
        binding.goodsPrice.setText("券前"+goodsListBean.getItemprice()+"元");
        binding.goodsPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        binding.goodsEndPrice.setText("¥"+goodsListBean.getItemendprice());
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        GridLayoutHelper helper = new GridLayoutHelper(2);
        helper.setAutoExpand(false);
        helper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                // position 当前总适配器的position ，
                //getStartPosition这个适配器之前的总数
                int new_position=position-getStartPosition();
                return new_position==0||new_position==1?1:2;
            }
        });
        return helper;
    }

}
