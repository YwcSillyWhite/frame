package com.purewhite.ywc.purewhite.config;

import android.view.View;

/**
 * Created by yuwenchao on 2018/11/6.
 */

public abstract class OnSingleListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        if (ClickUtils.clickable(v))
            onSingleClick(v);
    }

    public abstract void onSingleClick(View v);
}
