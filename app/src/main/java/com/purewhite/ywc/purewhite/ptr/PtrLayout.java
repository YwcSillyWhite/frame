package com.purewhite.ywc.purewhite.ptr;

import android.content.Context;
import android.util.AttributeSet;

import com.purewhite.ywc.purewhite.ptr.base.BasePtrFrameLayout;
import com.purewhite.ywc.purewhite.ptr.head.PtrFrameHead;

public class PtrLayout extends BasePtrFrameLayout {

    public PtrLayout(Context context) {
        super(context);
    }

    public PtrLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PtrLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();
        addHeadView(new PtrFrameHead(getContext()));
    }
}