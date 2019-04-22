package com.purewhite.ywc.purewhite.view.popupwindow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.purewhite.ywc.purewhite.config.click.OnSingleListener;

/**
 *
 * @author yuwenchao
 * @date 2018/11/20
 * 根据需求进行修改这个类
 */

public abstract class BasePopup extends PopupWindow{
    //全屏
    public final static int FULL=0;
    //dialog
    public final static int DIALOG=1;
    private View parentView;
    //这是
    public BasePopup(Context context,int layoutId) {
        this(context,layoutId,FULL);
    }

    public BasePopup(Context context,int layoutId,int type)
    {
        parentView=LayoutInflater.from(context).inflate(layoutId,null);
        switch (type)
        {
            case FULL:
                initFull();
                break;
            case DIALOG:
                initDialog();
                break;
        }
        setContentView(parentView);
    }

    private void initFull()
    {
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setOutsideTouchable(true);
        setTouchable(true);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        parentView.setOnClickListener(new OnSingleListener() {
            @Override
            public void onSingleClick(View v) {
                dismiss();
            }
        });
    }


    private void initDialog()
    {
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setOutsideTouchable(false);
        setTouchable(true);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
    }



    //修复8.0显示问题
    @Override
    public void showAsDropDown(View anchor) {
        if(getHeight()==ViewGroup.LayoutParams.MATCH_PARENT && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor);
    }


    //isback等于true的时候,点击放回先关闭popup
    protected void setBack(boolean isBack) {
        if (!isBack)
            return;
        parentView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK)
                    dismiss();
                return false;
            }
        });
    }

}
