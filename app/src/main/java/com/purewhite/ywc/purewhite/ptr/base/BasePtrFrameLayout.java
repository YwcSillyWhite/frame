package com.purewhite.ywc.purewhite.ptr.base;

import android.content.Context;
import android.util.AttributeSet;


import com.purewhite.ywc.purewhite.ptr.head.PtrFrameHead;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * @author yuwenchao
 */
public abstract class BasePtrFrameLayout extends PtrFrameLayout {

    public BasePtrFrameLayout(Context context) {
        this(context,null);
    }

    public BasePtrFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BasePtrFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    //初始化布局
    protected void init()
    {

        /**
         * ptr_resistance="1.7"：设置下拉的阻尼系数，值越大感觉越难下拉
         *
         * ptr_ratio_of_header_height_to_refresh：设置超过头部的多少时，释放可以执行刷新操作
         *
         * ptr_duration_to_close：设置下拉回弹的时间
         *
         * ptr_duration_to_close_header：设置刷新完成，头部回弹时间，注意和前一个进行区别
         *
         * ptr_keep_header_when_refresh：设置刷新的时候是否保持头部
         *
         * ptr_pull_to_fresh：设置下拉过程中执行刷新，我们一般设置为false
         */
        setResistance(1.7f);
        setRatioOfHeaderHeightToRefresh(1.2f);
        setDurationToClose(200);
        setDurationToCloseHeader(500);
        //默认就是true
        setKeepHeaderWhenRefresh(true);
        //默认就是false
        setPullToRefresh(false);
        //初始化不能刷新
        setEnabled(false);
    }


    @Override
    public void setPtrHandler(PtrHandler ptrHandler) {
        super.setPtrHandler(ptrHandler);
    }

    //添加头部
    public void addHeadView(PtrFrameHead ptrFrameHead)
    {
        setHeaderView(ptrFrameHead);
        addPtrUIHandler(ptrFrameHead);
    }


}
