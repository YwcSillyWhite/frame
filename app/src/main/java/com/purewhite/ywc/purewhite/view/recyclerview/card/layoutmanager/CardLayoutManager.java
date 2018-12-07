package com.purewhite.ywc.purewhite.view.recyclerview.card.layoutmanager;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * 卡片manager，防探探左滑关注，右滑取消
 * @author yuwenchao
 * 根据需求改动
 */
public class CardLayoutManager extends RecyclerView.LayoutManager {
    

    private int look_max_card_num=5;
    private int size;
    private float shrink_scale;

    public CardLayoutManager(float shrink_scale,int size) {
        this.size=size;
        this.shrink_scale=shrink_scale;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
//        // 先移除所有view
//        removeAllViews();
        // 在布局之前，将所有的子 View 先 Detach 掉，放入到 Scrap 缓存中
        detachAndScrapAttachedViews(recycler);
        int itemCount = getItemCount();
        //如果数据大于可见卡片
        int look_itemCount=itemCount>look_max_card_num?look_max_card_num:itemCount;
        for (int i = look_itemCount-1; i >=0; i--) {
            //当前item
            View view = recycler.getViewForPosition(i);
            addView(view);
            //测量item
            measureChild(view,0,0);
            //recycler的宽度-item的宽度等于剩余宽度
            int recycler_surplusWidth = getWidth() - getDecoratedMeasuredWidth(view);
            //recycler的高度-item的高度等于剩余高度
            int recycler_surplusHeight = getHeight() - getDecoratedMeasuredHeight(view);
            //设置recycler位置和大小（设置recycler的中心）
            layoutDecoratedWithMargins(view,recycler_surplusWidth/2,recycler_surplusHeight/2,
                    recycler_surplusWidth/2+getDecoratedMeasuredWidth(view),
                    recycler_surplusHeight/2+getDecoratedMeasuredHeight(view));
            view.setScaleX(1-i*shrink_scale);
            view.setScaleY(1-i*shrink_scale);
            view.setTranslationY(i*size);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.setTranslationZ(look_itemCount-i);
            }

        }
        
    }
}
