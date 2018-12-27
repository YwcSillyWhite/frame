package com.purewhite.ywc.purewhite.adapter.vlayout.child;

import android.util.SparseIntArray;

import com.purewhite.ywc.purewhite.adapter.bean.BindBean;
import com.purewhite.ywc.purewhite.adapter.callback.OnItemListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuwenchao
 */
public abstract class VlayoutBindTypeAdapter<T extends BindBean> extends VlayoutBindAdapter<T>{

    public VlayoutBindTypeAdapter() {
    }

    public VlayoutBindTypeAdapter(List<T> list) {
        super(list);
    }

    @Override
    protected int getDataType(int position) {
        return obtain(position)!=null?obtain(position).getBeanType():0;
    }
}
