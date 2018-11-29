package com.purewhite.ywc.purewhite.adapter.vlayout.child;

import android.util.SparseIntArray;

import com.purewhite.ywc.purewhite.adapter.recyclerview.bean.BindBean;
import com.purewhite.ywc.purewhite.adapter.recyclerview.io.OnItemListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuwenchao
 */
public abstract class VlayoutBindTypeAdapter<T extends BindBean> extends VlayoutBindAdapter<T>{

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

    @Override
    protected int getLayout(int viewType)
    {
        if (sparseIntArray!=null)
            return sparseIntArray.get(viewType);
        return 0;
    }

    public VlayoutBindTypeAdapter(List<T> list) {
        super(list);
    }

    public VlayoutBindTypeAdapter(OnItemListener<T> listener) {
        this(new ArrayList<T>(),listener);
    }

    public VlayoutBindTypeAdapter(List<T> list, OnItemListener<T> listener) {
        this(list);
        setOnItemListener(listener);
    }


    @Override
    public int getItemViewType(int position) {
        return getDataType(position);
    }

    private int getDataType(int position) {
        return obtain(position).getType();
    }
}
