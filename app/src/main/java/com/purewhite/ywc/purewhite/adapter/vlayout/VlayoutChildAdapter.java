package com.purewhite.ywc.purewhite.adapter.vlayout;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BaseViewHolder;

public abstract class VlayoutChildAdapter<V extends BaseViewHolder> extends DelegateAdapter.Adapter<V> {
    /**
     * LinearLayoutHelper: 线性布局
     * GridLayoutHelper: Grid布局， 支持横向的colspan
     * FixLayoutHelper: 固定布局，始终在屏幕固定位置显示
     * ScrollFixLayoutHelper: 固定布局，但之后当页面滑动到该图片区域才显示, 可以用来做返回顶部或其他书签等
     * FloatLayoutHelper: 浮动布局，可以固定显示在屏幕上，但用户可以拖拽其位置
     * ColumnLayoutHelper: 栏格布局，都布局在一排，可以配置不同列之间的宽度比值
     * SingleLayoutHelper: 通栏布局，只会显示一个组件View
     * OnePlusNLayoutHelper: 一拖N布局，可以配置1-5个子元素
     * StickyLayoutHelper: stikcy布局， 可以配置吸顶或者吸底
     * StaggeredGridLayoutHelper: 瀑布流布局，可配置间隔高度/宽度
     * @return
     */
    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return null;
    }


    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull V v, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    protected void onBindViewHolderWithOffset(V holder, int position, int offsetTotal) {
        super.onBindViewHolderWithOffset(holder, position, offsetTotal);
    }

}
