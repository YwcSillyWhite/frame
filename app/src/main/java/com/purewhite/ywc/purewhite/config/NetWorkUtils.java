package com.purewhite.ywc.purewhite.config;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.purewhite.ywc.purewhite.app.AppUtils;

/**
 * @author yuwenchao
 */
public class NetWorkUtils {

    public static boolean isNetworkConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager)
                AppUtils.getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }

}
