package com.purewhite.ywc.purewhite.ui.activity.main.adapter;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.recyclerview.adapter.BindTypeAdapter;
import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BindHolder;
import com.purewhite.ywc.purewhite.databinding.AdapterMianBinding;
import com.purewhite.ywc.purewhite.ui.bean.activity.main.MainBean;

import java.util.ArrayList;


/**
 * Created by yuwenchao on 2018/11/17
 * @author yuwenchao
 */

public class MainAdapter extends BindTypeAdapter<MainBean> {

    public MainAdapter() {
        super(new ArrayList<MainBean>());
        //1个使用
        addLayout(R.layout.adapter_mian);

    }

    @Override
    protected void onData(BindHolder holder, int position, MainBean mainBean) {
        AdapterMianBinding viewDataBinding = (AdapterMianBinding) holder.getViewDataBinding();
    }
}
