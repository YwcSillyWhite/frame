package com.purewhite.ywc.purewhite.adapter.viewholder;

import android.databinding.ViewDataBinding;

/**
 * Created by yuwenchao on 2018/11/17.
 */

public class BindHolder extends BaseViewHolder{

    private ViewDataBinding binding;

    public ViewDataBinding getBinding() {
        return binding;
    }

    public ViewDataBinding getViewDataBinding() {
        return binding;
    }

    public BindHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }


}
