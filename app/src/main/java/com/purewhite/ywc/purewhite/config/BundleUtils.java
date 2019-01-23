package com.purewhite.ywc.purewhite.config;

import android.os.Bundle;

public class BundleUtils {

    private Bundle bundle;
    public BundleUtils() {
         bundle = new Bundle();
    }

    public static BundleUtils newInstance() {
        return new BundleUtils();
    }

    public BundleUtils putInt(String key,int value)
    {
        bundle.putInt(key,value);
        return this;
    }

    public BundleUtils putString(String key,String value)
    {
        bundle.putString(key,value);
        return this;
    }

    public Bundle build()
    {
        return bundle;
    }
}
