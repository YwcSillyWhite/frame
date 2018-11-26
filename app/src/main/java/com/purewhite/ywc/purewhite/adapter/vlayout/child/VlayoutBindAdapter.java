package com.purewhite.ywc.purewhite.adapter.vlayout.child;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.purewhite.ywc.purewhite.adapter.recyclerview.bean.BindBean;
import com.purewhite.ywc.purewhite.adapter.recyclerview.io.OnItemListener;
import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BindHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class VlayoutBindAdapter<T extends BindBean> extends VlayoutBaseAdapter<T,BindHolder>{

    private SparseIntArray sparseIntArray;

    public void addLayout(int layoutId)
    {
        addLayout(0,layoutId);
    }

    public void addLayout(int viewType,int layoutId)
    {
        if (sparseIntArray==null)
        {
            sparseIntArray=new SparseIntArray();
        }
        sparseIntArray.put(viewType,layoutId);
    }

    private int getLayout(int viewType)
    {
        if (sparseIntArray!=null)
            return sparseIntArray.get(viewType);
        return 0;
    }

    public VlayoutBindAdapter(List<T> list) {
        super(list);
    }

    public VlayoutBindAdapter(OnItemListener<T> listener) {
        this(new ArrayList<T>());
        setOnItemListener(listener);
    }

    public VlayoutBindAdapter(List<T> list, OnItemListener<T> listener) {
        this(list);
        setOnItemListener(listener);
    }

    @Override
    protected BindHolder onCreateData(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                getLayout(viewType), parent, false);
        return new BindHolder(binding);
    }
}
