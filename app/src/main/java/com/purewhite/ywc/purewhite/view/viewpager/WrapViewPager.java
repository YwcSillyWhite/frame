package com.purewhite.ywc.purewhite.view.viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;

public class WrapViewPager extends ViewPager {

    //防止复用（在list里面使用复用）
    private int position=0;
    public void setPosition(int position) {
        this.position = position;
    }
    private SparseArray<Integer> sparseArray;
    public WrapViewPager(@NonNull Context context) {
        this(context,null);
    }

    public WrapViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    protected void initView(AttributeSet attrs) {
        sparseArray=new SparseArray<>();
    }


    /**
     * 这里我暂时只知道2种方式，这里写的一种就必须viewpager所有的子类同时显示。
     * 另一种是根据pageradapte instantiateItem 获取object。不过这一种必须重写pageradapter。
     * 在instantiateitem里面先删除view在加入，不过就会出现已存在父类异常，然后如果在listview里面使用如果不写object缓存会很卡
     * 这里我就不多解释，有需求可以加我qq1075770029
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int index = getCurrentItem();
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode==MeasureSpec.AT_MOST) {
            Integer integer = sparseArray.get(position * 1000 + index);
            if (integer != null && integer != 0) {
                heightMeasureSpec = integer;
            } else {
                View viewChild = null;
                if (getAdapter() != null) {
                    viewChild=getChildAt(index);
                    if (viewChild != null) {
                        viewChild.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0,
                                MeasureSpec.UNSPECIFIED));
                        heightMeasureSpec = MeasureSpec.makeMeasureSpec(viewChild.getMeasuredHeight(),
                                MeasureSpec.EXACTLY);
                        sparseArray.put(position * 1000 + index, heightMeasureSpec);
                    }
                }
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
