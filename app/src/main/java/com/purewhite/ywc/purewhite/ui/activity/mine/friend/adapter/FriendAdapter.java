package com.purewhite.ywc.purewhite.ui.activity.mine.friend.adapter;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.recyclerview.BindAdapter;
import com.purewhite.ywc.purewhite.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhite.bean.GoodsListBean;
import com.purewhite.ywc.purewhite.databinding.AdapterActivityFriendBinding;
import com.purewhite.ywc.purewhite.network.imageload.ImageLoader;

import java.util.ArrayList;

/**
 * @author yuwenchao
 */
public class FriendAdapter extends BindAdapter<GoodsListBean> {
    public FriendAdapter() {
        super(new ArrayList<GoodsListBean>());
        addLayout(R.layout.adapter_activity_friend);
    }

    @Override
    protected void onData(BindHolder holder, int position, GoodsListBean goodsListBean) {
        AdapterActivityFriendBinding dataBinding = (AdapterActivityFriendBinding) holder.getViewDataBinding();
        ImageLoader.newInstance().init(dataBinding.friendImg,goodsListBean.getItempic());
        dataBinding.friendText.setText(goodsListBean.getItemtitle());
    }
}
