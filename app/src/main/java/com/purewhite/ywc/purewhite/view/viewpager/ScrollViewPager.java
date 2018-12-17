package com.purewhite.ywc.purewhite.view.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.purewhite.ywc.purewhite.R;

public class ScrollViewPager extends WrapViewPager {

    private boolean scroll=false;


    public void setScroll(boolean scroll) {
        this.scroll = scroll;
    }

    public ScrollViewPager(@NonNull Context context) {
        this(context,null);
    }

    public ScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(AttributeSet attrs) {
        super.initView(attrs);
        if (attrs!=null)
        {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ScrollViewPager);
            scroll=typedArray.getBoolean(R.styleable.ScrollViewPager_scroll,scroll);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (scroll)
            return scroll;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (scroll)
            return true;
        return super.onTouchEvent(ev);
    }

}
