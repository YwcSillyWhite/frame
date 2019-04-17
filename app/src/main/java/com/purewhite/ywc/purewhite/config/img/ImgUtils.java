package com.purewhite.ywc.purewhite.config.img;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * 在Android应用中，图片的主要存在方式：
 * 以File的形式存在于SD卡中
 * 以Stream的形式存在于内存中
 * 以Bitmap形式存在于内存中
 *
 *
 * Android中图片是以位图（BitMap）形式存在的，位图常见的文件格式有：
 * .bmp
 * .jpg
 * .gif
 * .png
 *
 *
 *在SD卡中图片占用的内存与以Stream形式大小一样，均小于Bitmap形式下内存占比
 *既当图片从SD卡中以流的形式加载到内存中时大小是不会发生变化的，但是stream转化为Bitmap时，其大小会突然变大
 *
 *
 *
 *
 * @author yuwenchao
 */ //图片utils
public class ImgUtils {

    /**
     * view转换成图片
     * @param view
     */
    public static Bitmap obtainBitmap(View view)
    {
        if (view==null)
            return null;
        //创建一个bitmap的画板
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return bitmap;
    }

    /**
     * 尺寸压缩会改变图片的尺寸，即压缩图片宽度和高度的像素点
     * @param bitmap
     * @return
     */
//    public static Bitmap size(Bitmap bitmap)
//    {
//
//    }


    /**
     *
     * @param format
     * @param bitmap
     * @param quality
     * @return
     */
//    public static Bitmap comPress(Bitmap.CompressFormat format, Bitmap bitmap, int quality)
//    {
//        bitmap.compress(format,quality,new Arr)
//    }
}
