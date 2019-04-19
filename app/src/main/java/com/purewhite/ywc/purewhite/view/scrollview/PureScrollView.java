package com.purewhite.ywc.purewhite.view.scrollview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * @author yuwenchao
 */
public class PureScrollView extends ScrollView {
    //上次位置的xy
    private int lastX,lastY;
    private View childView;
    private Rect rect=new Rect();
    public PureScrollView(Context context) {
        super(context);
    }

    public PureScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PureScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount()>0)
        {
            childView=getChildAt(0);
        }
    }

    /**
     * 用于解决横竖冲突
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //是否拦截
        boolean intercept=false;
        //当前先x，y位置
        int new_x = (int) ev.getX();
        int new_y = (int) ev.getY();
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                lastX=new_x;
                lastY=new_y;
                break;
            case MotionEvent.ACTION_MOVE:
                //移动距离
                int move_x=Math.abs(new_x-lastX);
                int move_y=Math.abs(new_y-lastY);
                if (move_y>move_x)
                {
                    intercept=true;
                }
                lastX=new_x;
                lastY=new_y;
                break;
        }
        return intercept;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (childView!=null)
        {
            int new_y = (int) ev.getY();
            switch (ev.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    lastY=new_y;
                    break;
                case MotionEvent.ACTION_MOVE:
                    int move_y=new_y-lastY;
                    if (isMove())
                    {
                        if (rect.isEmpty())
                        {
                            rect.set(childView.getLeft(),childView.getTop(),childView.getRight(),childView.getBottom());
                        }
                        childView.layout(childView.getLeft(),childView.getTop()+move_y/2,childView.getRight(),childView.getBottom()+move_y/2);
                    }
                    lastY=move_y;
                    break;
            }
        }
        return super.onTouchEvent(ev);
    }


    private boolean isMove()
    {
        int child_MeasureHeight = childView.getMeasuredHeight();
        int measuredHeight = getMeasuredHeight();
        int move=child_MeasureHeight-measuredHeight;
        float scaleY = getScaleY();
        if (scaleY<=0||scaleY>=move)
        {
            return true;
        }
        return false;
    }
}
