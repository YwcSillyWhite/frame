package com.purewhite.ywc.purewhite.adapter.vlayout.child;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.purewhite.ywc.purewhite.adapter.callback.OnItemListener;
import com.purewhite.ywc.purewhite.adapter.viewholder.BindHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuwenchao
 */
public abstract class VlayoutBindAdapter<T> extends VlayoutBaseAdapter<T,BindHolder>{

    private SparseIntArray sparseIntArray;
    public VlayoutBindAdapter()
    {
        super(null);
    }

    public VlayoutBindAdapter(List<T> list) {
        super(list);
    }

    protected void addLayout(int layoutId)
    {
        addLayout(0,layoutId);
    }

    protected void addLayout(int viewType,int layoutId)
    {
        if (sparseIntArray==null)
        {
            sparseIntArray=new SparseIntArray();
        }
        sparseIntArray.put(viewType,layoutId);
    }


    @Override
    protected BindHolder onCreateData(ViewGroup parent, int viewType) {
        if (getLayout(viewType)!=-1)
        {
            ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    getLayout(viewType), parent, false);
            return new BindHolder(binding);
        }
        return null;
    }



    private int getLayout(int viewType)
    {
        if (sparseIntArray!=null) {
            return sparseIntArray.get(viewType);
        }
        return -1;
    }

}
