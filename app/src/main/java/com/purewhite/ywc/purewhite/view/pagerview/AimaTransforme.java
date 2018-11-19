package com.purewhite.ywc.purewhite.view.pagerview;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by yuwenchao on 2018/11/6.
 */

public class AimaTransforme implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.7f;



    //position表示临界点 -1是上一个页面，0表示当前页面，1表示下一个页面
    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);
        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);
        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            view.setAlpha(1 - position);
            // Counteract the default slide transition
            //把下一张图片移动到当前图片的下面
            view.setTranslationX(pageWidth * -position);
            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE+(1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}
