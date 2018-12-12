package com.purewhite.ywc.purewhite.adapter.recyclerview.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BindHolder;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yuwenchao
 * @date 2018/11/17
 * 使用数据里面的
 */

public abstract class BindAdapter<T> extends BaseAdapter<T,BindHolder>{
    private int layoutId;

    public BindAdapter(List<T> list) {
        super(list);
    }

    public BindAdapter(int layoutId)
    {
        this(new ArrayList<T>(),layoutId);
    }

    public BindAdapter(List<T> list,int layoutId) {
        this(new ArrayList<T>());
        this.layoutId=layoutId;
    }

    @Override
    protected BindHolder onCreateData(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                getLayout(viewType), parent, false);
        return new BindHolder(binding);
    }

    //返回布局id
    protected int getLayout(int viewType)
    {
        return layoutId;
    }



}
