package com.purewhite.ywc.purewhite.view.imageview;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class SameImageView extends AppCompatImageView {

    public SameImageView(Context context) {
        super(context);
    }

    public SameImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SameImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
