package com.purewhite.ywc.purewhite.adapter.recyclerview.adapter;

import android.util.SparseIntArray;

import com.purewhite.ywc.purewhite.adapter.recyclerview.bean.BindBean;

import java.util.List;

/**
 *
 * @author yuwenchao
 * @date 2018/11/17
 * 使用数据里面的
 */

public abstract class BindTypeAdapter<T extends BindBean> extends BindAdapter<T>{

    private SparseIntArray sparseIntArray;

    public BindTypeAdapter() {
        this(null);
    }
    public BindTypeAdapter(List<T> list) {
        super(list);
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


    @Override
    protected int getLayout(int viewType)
    {
        if (sparseIntArray!=null)
            return sparseIntArray.get(viewType);
        return -1;
    }

    @Override
    protected int getDataType(int position) {
        return obtainT(position)!=null?obtainT(position).getBeanType():0;
    }
}
