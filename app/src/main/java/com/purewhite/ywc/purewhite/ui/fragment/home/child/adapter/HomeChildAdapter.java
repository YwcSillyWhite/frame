package com.purewhite.ywc.purewhite.ui.fragment.home.child.adapter;

import android.view.View;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.recyclerview.adapter.BindTypeAdapter;
import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BindHolder;
import com.purewhite.ywc.purewhite.bean.main.MainBean;
import com.purewhite.ywc.purewhite.config.OnSingleListener;
import com.purewhite.ywc.purewhite.databinding.AdapterMianBinding;
import com.purewhite.ywc.purewhite.imageload.ImageLoader;

import java.util.ArrayList;


/**
 * Created by yuwenchao on 2018/11/17
 * @author yuwenchao
 */

public class  HomeChildAdapter extends BindTypeAdapter<MainBean.DataBean> {

    public HomeChildAdapter() {
        super(new ArrayList<MainBean.DataBean>());
        //1个使用
        addLayout(R.layout.adapter_mian);
    }


    @Override
    protected void onData(BindHolder holder, final int position, MainBean.DataBean dataBean) {
        AdapterMianBinding adapterMianBinding = (AdapterMianBinding) holder.getViewDataBinding();
        adapterMianBinding.shipTitle.setText(dataBean.getItem_title());
        ImageLoader.newInstance().init(adapterMianBinding.shipImg,dataBean.getItem_pic());

        adapterMianBinding.shipTitle.setOnClickListener(new OnSingleListener() {
            @Override
            public void onSingleClick(View v) {
                if (getOnItemListener()!=null)
                {
                    getOnItemListener().OnItemCall(HomeChildAdapter.this,v,position);
                }
            }
        });
    }
}
