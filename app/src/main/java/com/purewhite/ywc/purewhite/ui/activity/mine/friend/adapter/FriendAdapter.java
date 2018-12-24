package com.purewhite.ywc.purewhite.ui.activity.mine.friend.adapter;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.recyclerview.BindTypeAdapter;
import com.purewhite.ywc.purewhite.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhite.bean.main.MainBean;
import com.purewhite.ywc.purewhite.databinding.AdapterActivityFriendBinding;
import com.purewhite.ywc.purewhite.imageload.ImageLoader;

import java.util.ArrayList;

/**
 * @author yuwenchao
 */
public class FriendAdapter extends BindTypeAdapter<MainBean.DataBean> {
    public FriendAdapter() {
        super(new ArrayList<MainBean.DataBean>());
        addLayout(R.layout.adapter_activity_friend);
    }

    @Override
    protected void onData(BindHolder holder, int position, MainBean.DataBean dataBean) {
        AdapterActivityFriendBinding dataBinding = (AdapterActivityFriendBinding) holder.getViewDataBinding();
        ImageLoader.newInstance().init(dataBinding.friendImg,dataBean.getItem_pic());
        dataBinding.friendText.setText(dataBean.getItem_title());
    }
}
