package com.purewhite.ywc.purewhite.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.purewhite.ywc.purewhite.R;

/**
 * @author yuwenchao
 */
public class TrapezoidView extends View {
    private Paint paint;
    private int dimension;

    public TrapezoidView(Context context) {
        this(context,null);
    }

    public TrapezoidView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TrapezoidView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        paint=new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        if (attrs!=null)
        {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TrapezoidView);
            int color = typedArray.getColor(R.styleable.TrapezoidView_trap_color,Color.RED);
            paint.setColor(color);
            dimension = typedArray.getDimensionPixelOffset(R.styleable.TrapezoidView_trap_padding, 0);
        }
        //实心
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (dimension!=0)
        {
            int width = getWidth();
            int height = getHeight();
            Path path = new Path();
            path.moveTo(dimension, 0);
            path.lineTo(width-dimension, 0);
            path.lineTo(width, height);
            path.lineTo(0, height);
            path.close();
            canvas.drawPath(path, paint);
        }

    }
}
