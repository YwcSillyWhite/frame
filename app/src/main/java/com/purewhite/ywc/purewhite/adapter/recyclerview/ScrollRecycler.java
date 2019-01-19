package com.purewhite.ywc.purewhite.adapter.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author yuwenchao
 *
 * 处理和viewpage类型的滑动冲突
 */
public class ScrollRecycler extends RecyclerView {

    public ScrollRecycler(@NonNull Context context) {
        super(context);
    }

    public ScrollRecycler(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollRecycler(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    /**
     * requestDisallowInterceptTouchEvent()为true，
     * 表示自己的事件是不是我来处理，就是父类不走onInterceptTouchEvent
     */
    //手指按下时候xy的位置
    private float down_x,down_y;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        switch (ev.getAction())
//        {
//            case MotionEvent.ACTION_DOWN:
//                down_x=ev.getX();
//                down_y=ev.getY();
//                getParent().requestDisallowInterceptTouchEvent(true);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (Math.abs(ev.getX()-down_x)>Math.abs(ev.getY()-down_y))
//                {
//                    Log.d("ywc","自己处理");
//                    getParent().requestDisallowInterceptTouchEvent(true);
//                }
//                else
//                {
//                    Log.d("ywc","父亲处理");
//                    getParent().requestDisallowInterceptTouchEvent(false);
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                getParent().requestDisallowInterceptTouchEvent(false);
//                break;
//        }
        return super.dispatchTouchEvent(ev);
    }
}
