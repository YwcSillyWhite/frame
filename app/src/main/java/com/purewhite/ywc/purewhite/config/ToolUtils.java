package com.purewhite.ywc.purewhite.config;

import com.purewhite.ywc.purewhite.app.AppUtils;

public final class ToolUtils {
    /**
     * dp转px
     */
    public static int dip2px(float dipValue) {
        final float scale = AppUtils.getContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
