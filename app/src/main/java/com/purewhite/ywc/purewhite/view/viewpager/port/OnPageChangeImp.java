package com.purewhite.ywc.purewhite.view.viewpager.port;

import android.support.v4.view.ViewPager;

import com.purewhite.ywc.purewhite.adapter.callback.OnPageChangeListenerImp;
import com.purewhite.ywc.purewhite.view.viewpager.WrapViewPager;

/**
 * @author yuwenchao
 */
public class OnPageChangeImp extends OnPageChangeListenerImp {

    private ViewPager viewPager;

    public OnPageChangeImp(ViewPager viewPager) {
        this.viewPager = viewPager;
    }


    @Override
    public void onPageSelected(int i) {
        if (viewPager instanceof WrapViewPager)
            ((WrapViewPager) viewPager).resetHeight(i);
    }

}
