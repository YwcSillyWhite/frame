package com.purewhite.ywc.purewhite.view.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.purewhite.ywc.purewhite.R;

public class ScrollViewPager extends WrapViewPager {

    private boolean canScroll=false;

    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;
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
            canScroll=typedArray.getBoolean(R.styleable.ScrollViewPager_canScroll,false);
            Log.d("ywc","canScroll"+canScroll+"   "+toString());
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("ywc","canScroll"+canScroll);
        return canScroll?super.onInterceptTouchEvent(ev):true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("ywc",canScroll+"canScroll"+"  "+toString());
        return canScroll?super.onTouchEvent(ev):true;
    }


}
