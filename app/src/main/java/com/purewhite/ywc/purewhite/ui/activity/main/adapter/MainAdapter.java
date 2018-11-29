package com.purewhite.ywc.purewhite.ui.activity.main.adapter;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.recyclerview.adapter.BindTypeAdapter;
import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BindHolder;
import com.purewhite.ywc.purewhite.bean.main.MainBean;
import com.purewhite.ywc.purewhite.databinding.AdapterMianBinding;
import com.purewhite.ywc.purewhite.imageload.ImageLoader;

import java.util.ArrayList;


/**
 * Created by yuwenchao on 2018/11/17
 * @author yuwenchao
 */

public class MainAdapter extends BindTypeAdapter<MainBean.DataBean> {

    public MainAdapter() {
        super(new ArrayList<MainBean.DataBean>());
        //1个使用
        addLayout(R.layout.adapter_mian);
    }


    @Override
    protected void onData(BindHolder holder, int position, MainBean.DataBean dataBean) {
        AdapterMianBinding adapterMianBinding = (AdapterMianBinding) holder.getViewDataBinding();
        adapterMianBinding.shipTitle.setText(dataBean.getItem_title());
        ImageLoader.newInstance().init(adapterMianBinding.shipImg,dataBean.getItem_pic());
    }
}
