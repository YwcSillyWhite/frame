package com.purewhite.ywc.purewhite.config;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import com.purewhite.ywc.purewhite.app.AppUtils;

public class ToastUtils {
    private static Toast toast;
    private static final String TAG="纯白梦:";
    public static void show( String content)
    {
        if (toast==null)
        {
            Log.d("ywc","bucuo");
            toast=new Toast(AppUtils.getContext());
            toast.setDuration(Toast.LENGTH_SHORT);
        }
//        toast.setText(TAG+content);
//        toast.show();
    }
}
