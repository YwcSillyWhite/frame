package com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder;

import android.databinding.ViewDataBinding;

/**
 * Created by yuwenchao on 2018/11/17.
 */

public class BindHolder extends BaseViewHolder{

    private ViewDataBinding viewDataBinding;

    public ViewDataBinding getViewDataBinding() {
        return viewDataBinding;
    }

    public BindHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        viewDataBinding=binding;
    }


}
