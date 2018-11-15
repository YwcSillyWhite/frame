package com.purewhite.ywc.purewhite.imageload;

import android.widget.ImageView;

/**
 * Created by yuwenchao on 2018/11/14.
 * 图片加载包装接口
 */

public interface ImageLoadWrapper {
    //默认加载图片
     void init(ImageView imageView,Object url);
     //加载圆形图片
     void initCircle(ImageView imageView,Object url);
     //加载头像
     void initHead(ImageView imageView,Object url);
}
