package com.purewhite.ywc.purewhite.adapter.pagerview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuwenchao
 */
public abstract class BaseFragmentAdapter<T> extends FragmentPagerAdapter {

    protected List<T> mDatas;

    public BaseFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public BaseFragmentAdapter(FragmentManager fm, List<T> data) {
        this(fm);
        this.mDatas=data;
    }

    @Override
    public Fragment getItem(int i) {
        return getFragment(i);
    }

    protected abstract Fragment getFragment(int position);

    @Override
    public int getCount() {
        return mDatas!=null?mDatas.size():0;
    }


    public void flush(List<T> list)
    {
        mDatas=list!=null?list:new ArrayList<T>();
        notifyDataSetChanged();
    }
}
