package com.purewhite.ywc.purewhite.ui.fragment.home.child.adapter;

import android.support.v7.widget.GridLayoutManager;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.recyclerview.BindTypeAdapter;
import com.purewhite.ywc.purewhite.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhite.bean.main.MainBean;
import com.purewhite.ywc.purewhite.databinding.AdapterMianBinding;
import com.purewhite.ywc.purewhite.imageload.ImageLoader;


/**
 * Created by yuwenchao on 2018/11/17
 * @author yuwenchao
 */

public class  HomeChildAdapter extends BindTypeAdapter<MainBean.DataBean> {

    public HomeChildAdapter() {
        super();
        //1个使用
        addLayout(R.layout.adapter_mian);
//        addLayout(1,R.layout.adapter_);
    }


    @Override
    protected void onData(BindHolder holder, final int position, MainBean.DataBean dataBean) {
        AdapterMianBinding adapterMianBinding = (AdapterMianBinding) holder.getViewDataBinding();
        adapterMianBinding.shipTitle.setText(dataBean.getItem_title());
        ImageLoader.newInstance().init(adapterMianBinding.shipImg,dataBean.getItem_pic());
    }

    @Override
    protected int obtainDataSpanSize(int position, GridLayoutManager gridManager) {
        int surplus = position % 5;
        return surplus==0||surplus==1?3:2;
    }
}
