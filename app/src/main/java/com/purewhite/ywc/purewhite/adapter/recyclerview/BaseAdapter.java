package com.purewhite.ywc.purewhite.adapter.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.purewhite.ywc.purewhite.adapter.recyclerview.listener.OnItemListener;
import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BaseViewHolder;
import com.purewhite.ywc.purewhite.config.OnSingleListener;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by yuwenchao on 2018/11/15.
 */

public abstract class BaseAdapter<T,V extends BaseViewHolder> extends RecyclerView.Adapter<V>{

    private SparseIntArray sparseIntArray;
    private List<T> mData;
    //加载最多项
    private int pageSize=10;
    //头部
    private LinearLayout mHeaderLayout;
    //尾部
    private LinearLayout mFooterLayout;
    //是否允许加载更多
    private boolean canLoad=false;
    //是否加载更多
    private boolean isLoadMord=false;
    private final int HEAD_ITEM=10001;
    private final int FOOT_ITEM=10002;
    private final int LOAD_ITEM=10003;
    //监听
    private OnItemListener onItemListener;
    public OnItemListener getOnItemListener() {
        return onItemListener;
    }
    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public BaseAdapter(List<T> list) {
        this.mData = list!=null?list:new ArrayList<T>();
    }

    //加载更多的布局
    protected abstract int getLayoutLoad();
    //创建布局
    protected V createV(ViewGroup parent,int layout)
    {
        Context context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(layout, parent, false);
        return ((V) new BaseViewHolder(inflate));
    }

    protected V createV(View view)
    {
        return ((V) new BaseViewHolder(view));
    }

    //获取数据对象
    public T obtainT(int position)
    {
        if (position<mData.size())
        {
            return obtainT(position);
        }
        return null;
    }


    //添加布局
    protected void addLayout(int viewType,int layoutid)
    {
        if (sparseIntArray==null)
        {
            sparseIntArray=new SparseIntArray();
        }
        sparseIntArray.put(viewType,layoutid);
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        V viewhold;
        switch (viewType)
        {
            case HEAD_ITEM:
                viewhold=createV(mHeaderLayout);
                break;
            case FOOT_ITEM:
                viewhold=createV(mFooterLayout);
                break;
            case LOAD_ITEM:
                viewhold=createV(parent,getLayoutLoad());
                break;
                default:
                    viewhold=createV(parent,sparseIntArray.get(viewType));
                    //设置监听
                    bindViewListener(viewhold);
        }
        return viewhold;
    }

    protected void bindViewListener(final V viewhold)
    {
        if (viewhold == null) {
            return;
        }
        final View view = viewhold.itemView;
        if (view == null) {
            return;
        }
        if (getOnItemListener()!=null)
        {
            view.setOnClickListener(new OnSingleListener() {
                @Override
                public void onSingleClick(View v) {
                    getOnItemListener().OnItemCall(BaseAdapter.this,view,
                            viewhold.getLayoutPosition() - getFootCount());
                }
            });
        }

    }



    @Override
    public void onBindViewHolder(V holder, int position) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType!=HEAD_ITEM&&itemViewType!=FOOT_ITEM)
        {
            if (itemViewType==LOAD_ITEM)
            {

            }
            else
            {
                onData(holder,position,obtainT(position-getHeadCount()));
            }
        }
    }


    //赋值数据
    protected abstract void onData(V holder,int position,T t);




    //请不要重写这个方法,多布局重写getDataType这个方法
    @Override
    public int getItemViewType(int position) {
        if (position==0)
        {
            if (getHeadCount()!=0) {
                return HEAD_ITEM;
            } else {
                return super.getItemViewType(position);
            }
        }
        else if (position<getHeadCount()+getDataCount())
        {
            return getDataType(position);
        }
        else if(position<getHeadCount()+getDataCount()+getFootCount())
        {
            return FOOT_ITEM;
        }
        else
        {
            return LOAD_ITEM;
        }
    }


    protected int getDataType(int position)
    {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return getHeadCount()+getDataCount()+getFootCount()+getLoadCount();
    }


    //头部数
    public int getHeadCount()
    {
        if (mHeaderLayout!=null&&mHeaderLayout.getChildCount()>0)
            return 1;
        return 0;
    }

    //中间数据项
    public int getDataCount()
    {
        return mData!=null?mData.size():0;
    }

    //尾部数
    public int getFootCount()
    {
        if (mFooterLayout!=null&&mFooterLayout.getChildCount()>0)
            return 1;
        return 0;
    }

    //加载更多
    public int getLoadCount()
    {
        return isLoadMord?1:0;
    }


    //刷新数据
    public void flush(List<T> list)
    {
        mData=list!=null&&list.size()>0?list:new ArrayList<T>();
        if (mData.size()>=pageSize)
        {
            canLoad=true;
        }
        else
        {
            canLoad=false;
        }
        notifyDataSetChanged();
    }

    //添加数据
    public void addData(List<T> list)
    {
        if (list.size()>0)
        {
            mData.addAll(list);
            if (list.size()>=pageSize)
            {
                canLoad=true;
            }
            else
            {
                canLoad=false;
            }
            notifyItemRangeInserted(mData.size()-list.size() + getHeadCount(), list.size());
        }
        else
        {
            canLoad=false;
        }
    }



    //添加头尾
    public void addHeadView(View header)
    {
        addHeadView(header,-1);
    }

    public void addHeadView(View header,int indext)
    {
        addHeadView(header,indext,LinearLayout.VERTICAL);
    }

    public void addHeadView(View header, int index, int orientation) {
        if (mHeaderLayout==null)
        {
            mHeaderLayout=new LinearLayout(header.getContext());
            if (orientation==LinearLayout.VERTICAL)
            {
                mHeaderLayout.setOrientation(LinearLayout.VERTICAL);
                mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(MATCH_PARENT,WRAP_CONTENT));
            }
            else
            {
                mHeaderLayout.setOrientation(LinearLayout.HORIZONTAL);
                mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(WRAP_CONTENT,MATCH_PARENT));
            }
        }
        final int childCount = mHeaderLayout.getChildCount();
        if (index<0||index>childCount)
        {
            index=childCount;
        }
        mHeaderLayout.addView(header,index);
        if (mHeaderLayout.getChildCount()==1)
        {
            notifyItemInserted(0);
        }
    }


    //添加尾部
    public void addFoot(View foot)
    {
        addFoot(foot,-1);
    }

    public void addFoot(View foot,int index)
    {
        addFoot(foot,index,LinearLayout.VERTICAL);
    }

    public void addFoot(View header, int index, int orientation) {
        if (mFooterLayout==null)
        {
            mFooterLayout=new LinearLayout(header.getContext());
            if (orientation==LinearLayout.VERTICAL)
            {
                mFooterLayout.setOrientation(LinearLayout.VERTICAL);
                mFooterLayout.setLayoutParams(new RecyclerView.LayoutParams(MATCH_PARENT,WRAP_CONTENT));
            }
            else
            {
                mFooterLayout.setOrientation(LinearLayout.HORIZONTAL);
                mFooterLayout.setLayoutParams(new RecyclerView.LayoutParams(WRAP_CONTENT,MATCH_PARENT));
            }
        }
        final int childCount = mFooterLayout.getChildCount();
        if (index<0||index>childCount)
        {
            index=childCount;
        }
        mFooterLayout.addView(header,index);
        if (mFooterLayout.getChildCount()==1)
        {
            notifyItemInserted(getHeadCount()+getDataCount());
        }
    }









    private boolean noDataItem(int itemViewType)
    {
        return itemViewType==HEAD_ITEM||itemViewType==FOOT_ITEM||itemViewType==LOAD_ITEM;
    }

    //解决grid 添加头尾不占全部得问题
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager)
        {
            final GridLayoutManager gridManager = ((GridLayoutManager) layoutManager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return noDataItem(getItemViewType(position))?  1: gridManager.getSpanCount();
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(V holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if(layoutParams != null ) {
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams)
            {
                StaggeredGridLayoutManager.LayoutParams params =
                        (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                params.setFullSpan(noDataItem(holder.getItemViewType()));
            }
        }
    }
}
