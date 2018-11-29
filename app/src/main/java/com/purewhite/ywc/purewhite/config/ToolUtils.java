package com.purewhite.ywc.purewhite.config;

import android.content.Context;

public final class ToolUtils {
    /**
     * dp转px
     */
    public static int dip2px(float dipValue,Context context) {
        if (context != null) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dipValue * scale + 0.5f);
        }
        return 0;
    }
}
