package com.purewhite.ywc.purewhite.ui.adapter;

import android.graphics.Paint;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.recyclerview.BindAdapter;
import com.purewhite.ywc.purewhite.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhite.bean.GoodsListBean;
import com.purewhite.ywc.purewhite.config.StringUtils;
import com.purewhite.ywc.purewhite.databinding.AdapterGoodsListBinding;
import com.purewhite.ywc.purewhite.network.imageload.ImageLoader;

public class GoodsListAdapter extends BindAdapter<GoodsListBean> {

    public GoodsListAdapter() {
        addLayout(R.layout.adapter_goods_list);
    }

    @Override
    protected void onData(BindHolder holder, int position, GoodsListBean goodsListBean) {
        if (holder.getBinding() instanceof AdapterGoodsListBinding)
        {
            AdapterGoodsListBinding binding = (AdapterGoodsListBinding)holder.getBinding();
            ImageLoader.newInstance().init(binding.goodsImg,goodsListBean.getItempic());
            binding.goodsTitle.setText(goodsListBean.getItemtitle());
            binding.goodsSola.setText("月销"+StringUtils.obtainSola(goodsListBean.getItemsale())+"万");
            binding.shopName.setText(goodsListBean.getShopname());
            binding.goodsTkMoney.setText(goodsListBean.getTkmoney());
            binding.goodsPrice.setText("券前"+goodsListBean.getItemprice()+"元");
            binding.goodsPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            binding.goodsEndPrice.setText("¥"+goodsListBean.getItemendprice());
        }
    }
}
