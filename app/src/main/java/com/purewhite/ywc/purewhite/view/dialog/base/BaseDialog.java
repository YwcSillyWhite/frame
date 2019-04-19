package com.purewhite.ywc.purewhite.view.dialog.base;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.config.SizeUtils;
import com.purewhite.ywc.purewhite.view.dialog.callback.DialogCallBack;

/**
 * dialog基础类
 * @author yuwenchao
 */
public abstract class BaseDialog extends Dialog {

    protected final static int TOP=0;
    protected final static int BOTTOM=1;
    protected final static int LEFT=2;
    protected final static int RIGHT=3;
    protected DialogCallBack dialogCallBack;
    //上，下，左，右边
    private int anim[]={R.style.DialogTop,R.style.DialogBottom,R.style.DialogLeft,R.style.DialogRight};
    public void setDialogCallBack(DialogCallBack dialogCallBack) {
        this.dialogCallBack = dialogCallBack;
    }
    public BaseDialog(Context context) {
        this(context,R.style.BaseDialog);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
        //解决首次不能点击
        initView();
    }
    private void initView() {

        View view = LayoutInflater.from(getContext()).inflate(getLayout(), null);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(view,layoutParams);

        initView(view);
    }
    //布局id
    protected abstract int getLayout();
    protected abstract void initView(View view);
    //设置屏幕大小
    protected void setScreen(float num)
    {
        if (num<0||num>1)
            return;
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width =(int)(SizeUtils.getScreenWidth()*num);
        getWindow().setAttributes(lp);
    }
    protected void setAnim(int position)
    {
        if (position<0||position>=anim.length)
            return;
        getWindow().setWindowAnimations(anim[position]);
    }
}
