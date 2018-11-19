package com.purewhite.ywc.purewhite.activity.main.adapter;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.activity.main.bean.MainBean;
import com.purewhite.ywc.purewhite.adapter.recyclerview.BindAdapter;
import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BindHolder;
import com.purewhite.ywc.purewhite.databinding.ActivityMainBinding;
import com.purewhite.ywc.purewhite.databinding.AdapterMianBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuwenchao on 2018/11/17.
 */

public class MainAdapter extends BindAdapter<MainBean>{

    public MainAdapter() {
        super(new ArrayList<MainBean>());
        addLayout(R.layout.adapter_mian);
    }

    @Override
    protected void onData(BindHolder holder, int position, MainBean mainBean) {
        AdapterMianBinding viewDataBinding = (AdapterMianBinding) holder.getViewDataBinding();

    }
}
