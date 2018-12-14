package com.purewhite.ywc.purewhite.adapter.pagerview;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 *
 * @author yuwenchao
 * @date 2018/11/6
 */

public class BasePagerAdapter extends PagerAdapter {

    private List<View> list;

    public BasePagerAdapter(List<View> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(((View) object));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = list.get(position);
        container.addView(view);
        return view;
    }

    public void flush(List<View> list)
    {
        this.list=list;
        notifyDataSetChanged();
    }
}
