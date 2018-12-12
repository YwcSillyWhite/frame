package com.purewhite.ywc.purewhite.view.recyclerview.card.help;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.purewhite.ywc.purewhite.config.SizeUtils;
import com.purewhite.ywc.purewhite.view.recyclerview.card.io.CardScrollListener;
import com.purewhite.ywc.purewhite.view.recyclerview.card.layoutmanager.CardLayoutManager;

/**
 *
 * @author yuwenchao
 */
public class CardHelperCallBack extends ItemTouchHelper.Callback {

    private float scale;
    private CardLayoutManager cardLayoutManager;
    private CardScrollListener cradScrollListener;
    private int size;

    public CardLayoutManager getCardLayoutManager() {
        return cardLayoutManager;
    }

    public CardHelperCallBack(CardScrollListener cradScrollListener) {
        this(0.04f,10,cradScrollListener);
    }

    public CardHelperCallBack(float scale,int size, CardScrollListener cradScrollListener) {
        this.scale = scale;
        this.size=SizeUtils.dip2px(size);
        this.cradScrollListener = cradScrollListener;
        cardLayoutManager=new CardLayoutManager(scale,this.size);
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
    }

    /**
     * 是否支持水平滑动
     * @param recyclerView
     * @param viewHolder
     * @param viewHolder1
     * @return  false不支持 true支持
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }


    /**
     * 滑动多少视屏滚动
     * @param viewHolder
     * @return
     */
    @Override
    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        return 0.3f;
    }



    /**
     * 拽动
     * @param viewHolder
     * @param i 4左滑出，8右滑出 ，1上滑出，2下滑出
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (i)
        {
            case 4:
                cradScrollListener.call(viewHolder,CardScrollListener.LEFT);
                break;
            case 8:
                cradScrollListener.call(viewHolder,CardScrollListener.RIGHT);
                break;
        }
    }

    /**
     * 滑动中...,在绘画item的位置
     * @param c
     * @param recyclerView
     * @param viewHolder
     * @param dX
     * @param dY
     * @param actionState
     * @param isCurrentlyActive
     */
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        //这里我只写了水平滑动
        float maxMove = recyclerView.getWidth() * 0.5f;
        float moveScale=dX/maxMove;
        if (moveScale>1)
            moveScale=1;
        if (moveScale<-1)
            moveScale=-1;
        float justScale=moveScale>0?moveScale:-moveScale;
        int child_Count = recyclerView.getChildCount();
        for (int i = 0; i < child_Count; i++) {
            View view = recyclerView.getChildAt(i);
            view.setScaleX(1-(child_Count-i-1-justScale)*scale);
            view.setScaleY(1-(child_Count-i-1-justScale)*scale);
            view.setTranslationY((child_Count-i-1-justScale)*size);
        }
        cradScrollListener.scrollChange(viewHolder,moveScale);
    }


    //长按选中的viewholder
    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }

    //手指松开的viewholder
    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
    }

    //是否允许长按
    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    //是否允许拽动
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }
}
