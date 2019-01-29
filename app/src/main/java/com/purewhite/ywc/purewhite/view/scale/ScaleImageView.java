package com.purewhite.ywc.purewhite.view.scale;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.config.SizeUtils;

/**
 * 比例图片
 * @author yuwenchao
 */
public class ScaleImageView extends AppCompatImageView {
    private int scale_wight=1,scale_height=1;
    public ScaleImageView(Context context) {
        this(context,null);
    }

    public ScaleImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScaleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs) {
        if (attrs!=null)
        {
            TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ScaleView);
            scale_wight=typedArray.getInt(R.styleable.ScaleView_scale_wight,1);
            scale_height=typedArray.getInt(R.styleable.ScaleView_scale_height,1);
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
            height= (int)(width*1.0f*scale_height/scale_wight);
        }
        else if (MeasureSpec.EXACTLY==heightMode)
        {
            height=heightSize;
            width= (int)(height*1.0f*scale_wight/scale_height);
        }
        else if (MeasureSpec.AT_MOST==widthMode)
        {
            width=widthSize;
            height= (int)(width*1.0f*scale_height/scale_wight);
        }
        else
        {
            width=SizeUtils.dip2px(50);
            height= (int)(width*1.0f*scale_height/scale_wight);
        }
        setMeasuredDimension(width, height);
    }
}
