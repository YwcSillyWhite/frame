package com.purewhite.ywc.purewhite.view.progress;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * @author yuwenchao
 */
public class PureProgressBar extends ProgressBar {
    public PureProgressBar(Context context) {
        this(context,null);
    }

    public PureProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PureProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //根据资源设置进度条背景
    public void setProgressDrawable(int rescouse)
    {
        Drawable drawable = ContextCompat.getDrawable(getContext(), rescouse);
        setProgressDrawable(drawable);
    }


    public void startAnim(int endPosition)
    {
        startAnim(0,endPosition,0,100);
    }

    public void startAnim(int endPosition,int sumPosition)
    {
        startAnim(0,endPosition,0,sumPosition);
    }

    public void startAnim(int startPosition,int endPosition,int time,int sumPosition)
    {
        ValueAnimator valueAnimator=ValueAnimator.ofInt(startPosition,endPosition);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                setProgress(animatedValue);
            }
        });
        valueAnimator.setDuration(1000);
        if (sumPosition>0)
        {
            setMax(sumPosition);
        }
        valueAnimator.start();
    }
}
