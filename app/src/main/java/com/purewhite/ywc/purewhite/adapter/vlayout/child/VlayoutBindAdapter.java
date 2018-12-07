package com.purewhite.ywc.purewhite.adapter.vlayout.child;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.purewhite.ywc.purewhite.adapter.recyclerview.io.OnDataListener;
import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BindHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuwenchao
 */
public abstract class VlayoutBindAdapter<T> extends VlayoutBaseAdapter<T,BindHolder>{

    private int layoutId;

    protected int getLayout(int viewType)
    {
        return layoutId;
    }

    public VlayoutBindAdapter(List<T> list) {
        super(list);
    }

    public VlayoutBindAdapter(List<T> list,int layoutId)
    {
        this(list);
        this.layoutId=layoutId;
    }

    public VlayoutBindAdapter(int layoutId,OnDataListener listener) {
        this(new ArrayList<T>(),layoutId,listener);
    }

    public VlayoutBindAdapter(List<T> list,int layoutId, OnDataListener listener) {
        this(list);
        this.layoutId=layoutId;
        setOnItemListener(listener);
    }

    @Override
    protected BindHolder onCreateData(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                getLayout(viewType), parent, false);
        return new BindHolder(binding);
    }
}
