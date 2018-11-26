package com.purewhite.ywc.purewhite.adapter.vlayout.child;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.purewhite.ywc.purewhite.adapter.recyclerview.io.OnItemListener;
import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BaseViewHolder;
import com.purewhite.ywc.purewhite.config.OnSingleListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuwenchao
 */
public abstract class VlayoutBaseAdapter<T,V extends BaseViewHolder> extends DelegateAdapter.Adapter<V> {
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

    private List<T> mData;
    private OnItemListener onItemListener;
    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    private T obtain(int position)
    {
        if (position>=mData.size()-1)
        {
            return mData.get(position);
        }
        return null;
    }


    public VlayoutBaseAdapter(List<T> list) {
        this.mData = list!=null?list:new ArrayList<T>();
    }

    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        V viewHolder = onCreateData(viewGroup, viewType);
        bindClick(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull V v, int i) {

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    protected void onBindViewHolderWithOffset(V holder, int position, int offsetTotal) {
        super.onBindViewHolderWithOffset(holder, position, offsetTotal);
        onData(holder,position,obtain(position));
    }

    private void bindClick(final V holder) {
        if (holder==null)
            return;
        holder.itemView.setOnClickListener(new OnSingleListener() {
            @Override
            public void onSingleClick(View v) {
                int position=holder.getLayoutPosition();
                if (onItemListener!=null)
                {
                    onItemListener.OnItemCall(VlayoutBaseAdapter.this,v,position,obtain(position));
                }
            }
        });
    }


    //赋值数据
    protected abstract void onData(V holder,int position,T t);


    protected abstract V onCreateData(ViewGroup parent, int viewType);


    //刷新数据
    public void flush(List<T> list)
    {
        mData=list!=null&&list.size()>0?list:new ArrayList<T>();
        notifyDataSetChanged();
    }

    //添加数据
    public void addData(List<T> list)
    {
        if (mData==null&&mData.size()==0)
        {
            flush(list);
        }
        else
        {
            if (list!=null&&list.size()>0)
            {
                mData.addAll(list);
                notifyItemRangeInserted(mData.size()-list.size() , list.size());
            }
        }
    }
}
