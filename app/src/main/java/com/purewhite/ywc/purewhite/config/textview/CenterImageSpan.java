package com.purewhite.ywc.purewhite.config.textview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import java.lang.ref.WeakReference;

public class CenterImageSpan extends ImageSpan {
    private WeakReference<Drawable> weakReference;
    public CenterImageSpan(Context context, int resourceId, int verticalAlignment) {
        super(context, resourceId, verticalAlignment);
    }

//    @Override
//    public int getSize(Paint paint, CharSequence text, int start, int end,Paint.FontMetricsInt fm) {
//        Drawable drawable = getDrawable();
//        //绘画相关的工具类，只有top，bottom，left，right
//        Rect rect = drawable.getBounds();
//        if (fm != null) {
//            Paint.FontMetricsInt fmPaint = paint.getFontMetricsInt();
//            int fontHeight = fmPaint.bottom - fmPaint.top;
//            int drHeight = rect.bottom - rect.top;
//
//
//            fm.top = fm.drHeight;
//            fm.bottom = centerY + drHeight / 2;
//            fm.descent = fm.bottom;
//        }
//        return rect.right;
//
//
//        Drawable d = getDrawable();
//        Rect rect = d.getBounds();
//        if (fm != null) {
//            Paint.FontMetricsInt fmPaint = paint.getFontMetricsInt();
//            int fontHeight = fmPaint.bottom - fmPaint.top;
//            int drHeight = rect.bottom - rect.top;
//
//            int top = drHeight / 2 - fontHeight / 4;
//            int bottom = drHeight / 2 + fontHeight / 4;
//
//            fm.ascent = -bottom;
//            fm.top = -bottom;
//            fm.bottom = top;
//            fm.descent = top;
//        }
//        return rect.right;
//    }
}
