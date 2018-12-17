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
import com.purewhite.ywc.purewhite.config.OnSingleListener;

/**
 *这次优化准要是解决，如果底部view显示在当前fragment，那么子bottom该如何变化
 * @author yuwenchao
 * @date 2018/11/3
 */

public class BottomLayout extends LinearLayout{

    private OnBottomLayoutChageListener onBottomLayoutChageListener;
    private int bottomLayout_checkPosition;
    private SparseArray<BottomMenu> sparseArray;
    private SparseArray<View> viewArray;
    //上次选中bottom
    private View lastView;
    //其他view是否切换fragment
    private boolean restView_fragment;

    public void addOnBottomLayoutChageListener(OnBottomLayoutChageListener onBottomLayoutChageListener) {
        this.onBottomLayoutChageListener = onBottomLayoutChageListener;
    }

    public BottomLayout(Context context) {
        super(context,null);
    }

    public BottomLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);

    }

    public BottomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    //初始化
    private void initView(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.BottomLayout);
        bottomLayout_checkPosition = typedArray.getInteger(R.styleable.BottomLayout_checkPosition, 0);
        restView_fragment = typedArray.getBoolean(R.styleable.BottomLayout_restView_fragment, false);
    }


    //布局初始化
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        int childCount = getChildCount();
        if (childCount==0)
            return;
        viewArray=new SparseArray<>();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            viewArray.put(i,view);
            view.setOnClickListener(onSingleListener);
        }
        lastView = viewArray.get(bottomLayout_checkPosition)==null
                ?viewArray.get(0):viewArray.get(bottomLayout_checkPosition);
        //如果这个view是子类那么就设置默认
        if (lastView instanceof BottomMenu)
        {
            ((BottomMenu) lastView).setInitCheck();
        }
    }


    //设置选中id,初始设置了默认选中的
    public void setCheckPosition(int position)
    {
        if (onBottomLayoutChageListener==null||position>=sparseArray.size())
            return;
        View view=sparseArray.get(position);
        //第一种条件不管restview_fragment的值
        if (view instanceof BottomMenu&&lastView instanceof BottomMenu)
        {
            ((BottomMenu) lastView).setCheck(false);
            lastView=view;
            ((BottomMenu) lastView).setInitCheck();
        }
        else if (lastView instanceof BottomMenu)
        {
            //true 处理，false不做任何处理
            if (restView_fragment)
            {
                ((BottomMenu) lastView).setCheck(false);
                lastView=view;
            }
        }
        else
        {
            //restView_fragment = true
            lastView=view;
            if (view instanceof BottomMenu)
                ((BottomMenu) lastView).setInitCheck();
        }
        onBottomLayoutChageListener.onCheckChange(view);
    }











    /***** 事件监听 *****/

    private OnClickListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View view) {
            if (view instanceof BottomMenu)
                childChange(((BottomMenu) view));
            else {
                childChange(view);
            }
        }
    };

    private void childChange(BottomMenu view)
    {
        if (onBottomLayoutChageListener==null||lastView==view)
            return;
        if (ClickUtils.clickable(this))
        {
            if (lastView instanceof BottomMenu)
                //清楚上次选中
                ((BottomMenu) lastView).setCheck(false);
            //设置选择lastBottom
            lastView=view;
            view.setCheck(true);
            onBottomLayoutChageListener.onCheckChange(view);

        }
    }


    private void childChange(View view)
    {
        if (onBottomLayoutChageListener==null||lastView==view)
            return;
        if (restView_fragment)
        {
            if (lastView instanceof BottomMenu)
                ((BottomMenu) lastView).setCheck(false);
            lastView=view;
        }
        onBottomLayoutChageListener.onCheckChange(view);
    }

    /***** io接口  *****/
    public interface OnBottomLayoutChageListener
    {
        void onCheckChange(View view);
    }
}
