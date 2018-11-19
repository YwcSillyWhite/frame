package com.purewhite.ywc.purewhite.ui.activity.main.adapter;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.ui.Bean.activity.main.MainBean;
import com.purewhite.ywc.purewhite.adapter.recyclerview.BindAdapter;
import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BindHolder;
import com.purewhite.ywc.purewhite.databinding.AdapterMianBinding;

import java.util.ArrayList;


/**
 * Created by yuwenchao on 2018/11/17
 */

public class MainAdapter extends BindAdapter<MainBean>{

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
