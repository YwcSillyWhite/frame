package com.purewhite.ywc.purewhite.imageload;

import android.os.Bundle;

/**
 * Created by yuwenchao on 2018/11/14.
 * 图片加载
 */

public class ImageLoader {
    private static volatile ImageLoadWrapperImp imageLoadWrapperImp;
    public static ImageLoadWrapper newInstance() {
        if (imageLoadWrapperImp==null)
        {
            synchronized (ImageLoader.class)
            {
                if (imageLoadWrapperImp==null)
                {
                    imageLoadWrapperImp=new ImageLoadWrapperImp();
                }
            }
        }
        return imageLoadWrapperImp;
    }

    public ImageLoader() {
        throw new UnsupportedOperationException("you can not create object");
    }
}
