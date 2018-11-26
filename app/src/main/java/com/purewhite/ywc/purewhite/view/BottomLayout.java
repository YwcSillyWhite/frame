package com.purewhite.ywc.purewhite.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.config.ClickUtils;

/**
 *
 * @author yuwenchao
 * @date 2018/11/3
 */

public class BottomLayout extends LinearLayout implements BottomMenu.OnBottomListener{

    private OnBottomLayoutChageListener onBottomLayoutChageListener;
    private int BottomLayout_checkPosition;
    private SparseArray<BottomMenu> sparseArray;
    //是否运行
    private boolean isRunning=false;
    public boolean isRunning() {
        return isRunning;
    }
    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void addOnBottomLayoutChageListener(OnBottomLayoutChageListener onBottomLayoutChageListener) {
        this.onBottomLayoutChageListener = onBottomLayoutChageListener;
    }
    //上次选中bottom
    private BottomMenu lastBottom;


    public BottomLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    //初始化
    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomLayout);
        BottomLayout_checkPosition = typedArray.getInteger(R.styleable.BottomLayout_checkPosition, 0);
    }


    //布局初始化
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        int childCount = getChildCount();
        sparseArray=new SparseArray<>();
        int position=-1;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view instanceof BottomMenu)
            {
                position++;
                ((BottomMenu) view).setOnBottomListener(this);
                sparseArray.put(position, ((BottomMenu) view));
            }
        }
        if (position>=BottomLayout_checkPosition)
            BottomLayout_checkPosition=0;
        lastBottom=sparseArray.get(BottomLayout_checkPosition);
        lastBottom.setInitCheck();
    }

    //设置选中id,初始设置了默认选中的
    public void setCheckId(int id)
    {
        if (isRunning)
            return;
        for (int i = 0; i < sparseArray.size(); i++) {
            if (sparseArray.get(i).getId()==id)
            {
                lastBottom.setCheck(false);
                lastBottom=sparseArray.get(i);
                lastBottom.setInitCheck();
                onBottomLayoutChageListener.onCheckChage(lastBottom);
            }
        }
    }

    //设置选中id,初始设置了默认选中的
    public void setCheckPosition(int position)
    {
        if (isRunning)
            return;
        if (position>=sparseArray.size())
            return;
        lastBottom.setCheck(false);
        lastBottom=sparseArray.get(position);
        lastBottom.setInitCheck();
        onBottomLayoutChageListener.onCheckChage(lastBottom);
    }


    @Override
    public void onSingleTap(BottomMenu view) {
        childChage(view);
    }

    @Override
    public void onDoubleTap(BottomMenu view) {
        childChage(view);
    }


    public void childChage(BottomMenu view) {
        if (isRunning||lastBottom==view)
            return;
        if (ClickUtils.clickable(this))
        {
            //清楚上次选中
            lastBottom.setCheck(false);
            //设置选择lastBottom
            lastBottom=view;
            lastBottom.setCheck(true);
            if (onBottomLayoutChageListener!=null)
                onBottomLayoutChageListener.onCheckChage(view);
        }
    }


    public interface OnBottomLayoutChageListener
    {
        void onCheckChage(BottomMenu view);
    }
}
