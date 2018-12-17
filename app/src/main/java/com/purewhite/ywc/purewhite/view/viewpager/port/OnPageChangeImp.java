package com.purewhite.ywc.purewhite.view.viewpager.port;

import android.support.v4.view.ViewPager;

import com.purewhite.ywc.purewhite.view.viewpager.WrapViewPager;

public class OnPageChangeImp implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;

    public OnPageChangeImp(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        if (viewPager instanceof WrapViewPager)
            ((WrapViewPager) viewPager).resetHeight(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


}
