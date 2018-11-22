package com.purewhite.ywc.purewhite.config;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;

//图片utils
public class ImgUtils {

    /**
     * view转换成图片
     * @param view
     */
    public static Bitmap obtain(View view)
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
}
