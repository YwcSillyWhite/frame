package com.purewhite.ywc.purewhite.view.dialog;

import android.content.Context;
import android.view.View;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.view.dialog.base.BaseDialog;

import java.util.Random;

public class MainDialog extends BaseDialog {
    public MainDialog(Context context) {
        super(context);
        setScreen(0.8f);
        int i = new Random().nextInt(3);
        setAnim(i);
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_main;
    }

    @Override
    protected void initView(View view) {

    }

}
