package com.purewhite.ywc.purewhite.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.purewhite.ywc.purewhite.R;

/**
 * Created by yuwenchao on 2018/11/2.
 */

public class BottomMenu extends RelativeLayout implements GestureDetector.OnDoubleTapListener{
    //是否选中
    private boolean isCheck;
    //图片
    private ImageView bottomImg;
    //内容
    private TextView bottomTv;
    //消息数
    private TextView bottomNum;
    private int BottomMenu_checkTv_true;
    private int BottomMenu_checkTv_flase;
    private int BottomMenu_checkImg_flase;
    private int BottomMenu_checkImg_true;
    private float dimensionPixelSize;
    //手势监听
    private GestureDetector gestureDetector;
    private boolean BottomMenu_anmiable;
    private String BottomMenu_text;

    public void setOnBottomListener(OnBottomListener onBottomListener) {
        this.onBottomListener = onBottomListener;
    }

    //监听
    private OnBottomListener onBottomListener;

    public BottomMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);

    }

    private void initView(Context context, AttributeSet attrs) {
        //初始化布局
        View view = View.inflate(context, R.layout.view_bottommenu, this);
        bottomImg = ((ImageView) view.findViewById(R.id.bottomImg));
        bottomTv = ((TextView) view.findViewById(R.id.bottomTv));
        bottomNum = ((TextView) view.findViewById(R.id.bottomNum));
        //获取xml属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.BottomMenu);
        BottomMenu_checkTv_true = typedArray.getColor(R.styleable.BottomMenu_checkTv_true, 0Xfff);
        BottomMenu_checkTv_flase = typedArray.getColor(R.styleable.BottomMenu_checkTv_flase, 0X333);
        BottomMenu_checkImg_flase = typedArray.getResourceId(R.styleable.BottomMenu_checkImg_flase, -1);
        BottomMenu_checkImg_true = typedArray.getResourceId(R.styleable.BottomMenu_checkImg_true, -1);
        dimensionPixelSize = typedArray.getDimension(R.styleable.BottomMenu_Tvsize, 15);
        BottomMenu_anmiable = typedArray.getBoolean(R.styleable.BottomMenu_anmiable, true);
        BottomMenu_text = typedArray.getString(R.styleable.BottomMenu_text);
        //设置数值
        setMessageNum(0);
        bottomTv.setTextSize(TypedValue.COMPLEX_UNIT_PX,dimensionPixelSize);
        bottomTv.setText(BottomMenu_text!=null&&!BottomMenu_text.isEmpty()?BottomMenu_text:"设置初始值");
        setData();
        //设置手势监听
        gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener());
        gestureDetector.setOnDoubleTapListener(this);
        //动画

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }

    //动画
    private void anim()
    {
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1);
        setPivotX(getWidth()/2);
        setPivotY(getHeight()/2);
        valueAnimator.setDuration(200);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                if(progress < 0.3f){
                    setScaleX(1-progress*0.2f/0.3f);
                    setScaleY(1-progress*0.2f/0.3f);
                } else {
                    setScaleX(0.7f+progress*0.3f);
                    setScaleY(0.7f+progress*0.3f);
                }
            }
        });
        valueAnimator.start();
    }

    //设置是否选中
    public void setCheck(boolean check)
    {
        if (isCheck==check)
            return;
        isCheck=check;
        setData();
        if (isCheck)
        {
            if (BottomMenu_anmiable)
                anim();
            setMessageNum(0);
        }
    }

    public void setInitCheck() {
        isCheck=true;
        setData();
    }

    //设置选中状态
    private void setData()
    {
        bottomImg.setImageResource(isCheck?BottomMenu_checkImg_true:BottomMenu_checkImg_flase);
        bottomTv.setTextColor(isCheck?BottomMenu_checkTv_true:BottomMenu_checkTv_flase);
    }

    //设置消息数
    public void setMessageNum(int num)
    {
        if (num<=0)
        {
            bottomNum.setVisibility(GONE);
        }
        else
        {
            bottomNum.setVisibility(VISIBLE);
            if (num>99)
            {
                bottomNum.setText("99+");
            }
            else
            {
                bottomNum.setText(num+"");
            }
        }
    }

    //单点
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        if (onBottomListener!=null)
            onBottomListener.onSingleTap(this);
        return false;
    }

    //双击
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        if (onBottomListener!=null)
            onBottomListener.onDoubleTap(this);
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }


    public interface OnBottomListener
    {
        void onSingleTap(BottomMenu view);
        void onDoubleTap(BottomMenu view);
    }
}
