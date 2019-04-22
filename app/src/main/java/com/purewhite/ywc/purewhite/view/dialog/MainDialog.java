package com.purewhite.ywc.purewhite.view.dialog;

import android.content.Context;
import android.view.View;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.config.click.OnSingleListener;
import com.purewhite.ywc.purewhite.view.dialog.base.BaseDialog;
import com.purewhite.ywc.purewhite.view.dialog.callback.DialogCallBackImp;

/**
 * @author yuwenchao
 */
public class MainDialog extends BaseDialog {


    public MainDialog(Context context) {
        super(context);
        setScreen(0.8f);
        setAnim(LEFT);
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_main;
    }

    @Override
    protected void initView(View view) {

        view.findViewById(R.id.dialog_clear).setOnClickListener(new OnSingleListener() {
            @Override
            public void onSingleClick(View v) {
                dismiss();
                if (dialogCallBack!=null)
                {
                    dialogCallBack.callback(DialogCallBackImp.clear);
                }
            }
        });


        view.findViewById(R.id.dialog_sure).setOnClickListener(new OnSingleListener() {
            @Override
            public void onSingleClick(View v) {
                dismiss();
                if (dialogCallBack!=null)
                {
                    dialogCallBack.callback(DialogCallBackImp.sure);
                }
            }
        });

    }

}
