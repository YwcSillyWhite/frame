package com.purewhite.ywc.purewhite.view.imageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.config.SizeUtils;

/**
 * 比例图片
 * @author yuwenchao
 */
public class RatioImageView extends AppCompatImageView {

    private float scale=1f;
    public RatioImageView(Context context) {
        this(context,null);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs) {
        if (attrs!=null)
        {
            TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.RatioImageView);
            scale = typedArray.getFloat(R.styleable.RatioImageView_scale, scale);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width, height;
        if (MeasureSpec.EXACTLY==widthMode)
        {
            width=widthSize;
            height= ((int) (widthSize * scale));
        }
        else if (MeasureSpec.EXACTLY==heightMode)
        {
            height=heightSize;
            width= ((int) (heightSize / scale));
        }
        else if (MeasureSpec.AT_MOST==widthMode)
        {
            width=widthSize;
            height= ((int) (widthSize * scale));
        }
        else
        {
            width=SizeUtils.dip2px(50);
            height= ((int) (width * scale));
        }
        setMeasuredDimension(width, height);
    }
}
