package com.purewhite.ywc.purewhite.view.recyclerview;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.purewhite.ywc.purewhite.adapter.recyclerview.adapter.BaseAdapter;

/**
 * item的间距
 * @author yuwenchao
 */
public class OneDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private int num;
    public OneDecoration(int space,int num) {
        this.space=space;
        this.num=num;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter!=null)
        {
            if (adapter instanceof BaseAdapter) {
                BaseAdapter baseAdapter = (BaseAdapter) adapter;
                int childAdapterPosition = parent.getChildAdapterPosition(view);
                if (baseAdapter.getHeadCount() - 1 < childAdapterPosition
                        && childAdapterPosition < baseAdapter.getDataCount() + baseAdapter.getHeadCount())
                {
                    int finish_num=childAdapterPosition% num;
                    outRect.bottom=space;
                    if (finish_num==0&&finish_num==1)
                    {
                        outRect.left=space;
                        outRect.right=space;
                    }
                    else if(finish_num==0)
                    {
                        outRect.left=space;
                        outRect.right=space/2;
                    }
                    else if(finish_num==finish_num-1)
                    {
                        outRect.left=space/2;
                        outRect.right=space;
                    }
                    else
                    {
                        outRect.left=space/2;
                        outRect.right=space/2;
                    }
                }
                else
                {
                    outRect.left=0;
                    outRect.right=0;
                    outRect.bottom=0;
                }
            }
        }
    }
}
