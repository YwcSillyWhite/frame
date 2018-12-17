package com.purewhite.ywc.purewhite.view.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.view.viewpager.port.OnPageChangeImp;

public class WrapViewPager extends ViewPager {

    //防止复用（在list里面使用复用）
    private int position=0;
    private int paddingBottom;
    private int paddingTop;
    private boolean cache=false;
    //子类的最小高度，如果使用缓存的时候记得子类会不会存在最小高度=
    private float child_height;
    public void setPosition(int position) {
        this.position = position;
    }
    private int height;
    private OnPageChangeImp onPageChangeImp;
    private SparseArray<Integer> sparseArray;

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public void setChild_height(float child_height) {
        this.child_height = child_height;
    }

    public WrapViewPager(@NonNull Context context) {
        this(context,null);
    }

    public WrapViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    protected void initView(AttributeSet attrs) {
        onPageChangeImp = new OnPageChangeImp(this);
        addOnPageChangeListener(onPageChangeImp);
        paddingBottom = getPaddingBottom();
        paddingTop = getPaddingTop();
        if (attrs!=null)
        {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.WrapViewPager);
            cache = typedArray.getBoolean(R.styleable.WrapViewPager_cache, cache);
            child_height = typedArray.getDimension(R.styleable.WrapViewPager_child_height, 0);
        }
        if (cache)
        {
            sparseArray=new SparseArray<>();
        }
    }


    /**
     * 这里我暂时只知道2种方式，这里写的一种就必须viewpager所有的子类同时显示。
     * 另一种是根据pageradapte instantiateItem 获取object。不过这一种必须重写pageradapter。
     * 在instantiateitem里面先删除view在加入，不过就会出现已存在父类异常，然后如果在listview里面使用如果不写object缓存会很卡
     * 这里我就不多解释，有需求可以加我qq1075770029
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int index = getCurrentItem();
        if (cache)
        {
            int cacheHeight = sparseArray.get(position*1000+index,0);
            if (cacheHeight>child_height+paddingBottom+paddingTop)
            {
                height=cacheHeight;
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            }
            else
            {
                View view = getChildAt(index);
                if (view!=null)
                {
                    view.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                    height = view.getMeasuredHeight()+paddingTop+paddingBottom;
                    heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
                    sparseArray.put(position*1000+index,height);
                }
            }
        }
        else
        {
            View view = getChildAt(index);
            if (view!=null)
            {
                view.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                height = view.getMeasuredHeight()+paddingTop+paddingBottom;
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    /**
     * 滑动重新定义viewpager的高度
     * @param current
     */
    public void resetHeight(int current) {
        if (height>paddingTop+paddingBottom)
        {
            View view = getChildAt(current);
            if (view!=null)
            {
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                if (layoutParams == null) {
                    layoutParams=new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,height);
                } else {
                    layoutParams.height = height;
                }
                setLayoutParams(layoutParams);
            }
        }

    }


    /**  在 activity活fragment销毁的时候移除这个实现类，属于代码优化吧 **/
    public void removeOnPageChangeImp()
    {
        removeOnPageChangeListener(onPageChangeImp);
    }

}
