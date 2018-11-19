package com.purewhite.ywc.purewhite.adapter.recyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.recyclerview.bean.BindBean;
import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BindHolder;

import java.util.List;

/**
 * Created by yuwenchao on 2018/11/17.
 * 使用数据里面的
 */

public abstract class BindAdapter<T extends BindBean> extends BaseAdapter<T,BindHolder>{

    private SparseIntArray sparseIntArray;

    public BindAdapter(List<T> list) {
        super(list);
    }

    public BindAdapter()
    {
        this(null);
    }

    @Override
    protected BindHolder onCreateData(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                getLayout(viewType), parent, false);
        return new BindHolder(binding);
    }

    protected void addLayout(int viewType,int layout)
    {
        if (sparseIntArray==null)
        {
            sparseIntArray=new SparseIntArray();
        }
        sparseIntArray.put(viewType,layout);
    }

    protected void addLayout(int layout)
    {
        addLayout(0,layout);
    }

    protected int getLayout(int viewType)
    {
        if (sparseIntArray!=null)
            return sparseIntArray.get(viewType);
        return -1;
    }

    @Override
    protected int getDataType(int position) {
        return obtainT(position).getType();
    }

    @Override
    protected abstract void onData(BindHolder holder, int position, T t);

}
