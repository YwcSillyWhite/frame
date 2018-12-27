package com.purewhite.ywc.purewhite.adapter.pagerview;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.purewhite.ywc.purewhite.adapter.callback.OnItemListener;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePagerAdapter<T> extends PagerAdapter {

    private List<T> data;
    private SparseArray<View> sparseArray;

    public BasePagerAdapter() {
        this(null);
    }

    public BasePagerAdapter(List<T> data) {
        this.data=data==null?new ArrayList<T>():data;
        sparseArray=new SparseArray<>();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = sparseArray.get(position);
        if (view!=null)
        {
            container.removeView(view);
        }
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = sparseArray.get(position);
        if (view==null)
        {
            view= obtainView(position);
            sparseArray.put(position,view);
        }
        container.addView(view);
        return view;
    }

    protected abstract View obtainView(int position);

    protected T obtainT(int position)
    {
        return data!=null&&data.size()>position?data.get(position):null;
    }

    //初始化就创建所有view
    protected void createSpare()
    {
        if (data!=null&&data.size()>0)
        {
            for (int i = 0; i < data.size(); i++) {
                sparseArray.put(i,obtainView(i));
            }
        }
    }


    public void flush(List<T> list)
    {
        data=list;
        sparseArray.clear();
        notifyDataSetChanged();
    }


    public void flushCreateSpare(List<T> list)
    {
        data=list;
        sparseArray.clear();
        createSpare();
        notifyDataSetChanged();
    }

}
