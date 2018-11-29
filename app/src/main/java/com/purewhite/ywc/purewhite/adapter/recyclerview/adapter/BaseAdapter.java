package com.purewhite.ywc.purewhite.adapter.recyclerview.adapter;

import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.purewhite.ywc.purewhite.adapter.recyclerview.io.OnItemListener;
import com.purewhite.ywc.purewhite.adapter.recyclerview.loadview.LoadView;
import com.purewhite.ywc.purewhite.adapter.recyclerview.loadview.LoadViewImp;
import com.purewhite.ywc.purewhite.adapter.recyclerview.loadview.io.OnLoadListenerImp;
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

    //用于延迟
    private Handler handler=new Handler();
    //加载布局
    private LoadView loadView=new LoadViewImp();
    public void setLoadView(LoadView loadView) {
        if (loadView==null)
            throw new UnsupportedOperationException("loadvuew can not null");
        this.loadView = loadView;
    }

    private List<T> mData;
    //加载最多项
    private int pageSize=10;
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    //头部
    private LinearLayout mHeaderLayout;
    //尾部
    private LinearLayout mFooterLayout;
    private final int HEAD_ITEM=10001;
    private final int FOOT_ITEM=10002;
    private final int LOAD_ITEM=10003;
    //监听
    private OnItemListener<T> onItemListener;
    public OnItemListener getOnItemListener() {
        return onItemListener;
    }
    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    private OnLoadListenerImp onLoadListenerImp;

    public void setOnLoadListenerImp(OnLoadListenerImp onLoadListenerImp) {
        this.onLoadListenerImp = onLoadListenerImp;
    }

    public BaseAdapter(List<T> list) {
        this.mData = list!=null?list:new ArrayList<T>();
    }

    private V createV(View view)
    {
        return ((V) new BaseViewHolder(view));
    }

    //获取数据对象
    public T obtainT(int position)
    {
        if (position<mData.size())
        {
            return mData.get(position);
        }
        return null;
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
                View viewLoad = LayoutInflater.from(parent.getContext())
                        .inflate(loadView.getLayoutId(), parent, false);
                viewhold=createV(viewLoad);
                viewLoad.setOnClickListener(new OnSingleListener() {
                    @Override
                    public void onSingleClick(View v) {
                        //加载失败，点击重新加载
                        if (loadView.getState()==LoadView.STATE_FAIL)
                        {
                            loadView.setState(LoadView.STATE_LOAD);
                            onLoadListenerImp.loadAgain();
                        }
                    }
                });
                break;
                default:
                    viewhold = onCreateData(parent,viewType);
                    //设置监听
                    bindViewListener(viewhold);
        }
        return viewhold;
    }

    protected abstract V onCreateData(ViewGroup parent, int viewType);

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
                    int position=viewhold.getLayoutPosition() - getFootCount();
                    getOnItemListener().OnItemCall(BaseAdapter.this,view, position,obtainT(position));
                }
            });
        }

    }



    @Override
    public void onBindViewHolder(V holder, int position) {
        loadMore(position);
        int itemViewType = holder.getItemViewType();
        if (itemViewType==LOAD_ITEM)
        {
            loadView.onBindView(holder);
        }
        else if (itemViewType!=HEAD_ITEM&&itemViewType!=FOOT_ITEM)
        {
            onData(holder,position,obtainT(position-getHeadCount()));
        }
    }

    //判断是不是加载更多
    protected  void loadMore(int position)
    {
        if (getLoadCount()==0||position<getItemCount()-1)
            return;
        //加载结束
        if (loadView.getState()==LoadView.STATE_FINISH_TRUE) {
            loadView.setState(LoadView.STATE_LOAD);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    onLoadListenerImp.onPullUp();
                }
            }, 200);
        }
    }

    //赋值数据
    protected abstract void onData(V holder,int position,T t);


    //请不要重写这个方法,多布局重写getDataType这个方法
    @Override
    public int getItemViewType(int position) {
        if (position==0&&getHeadCount()!=0)
        {
            return HEAD_ITEM;
        }
        else if (position<getHeadCount()+getDataCount())
        {
            return getDataType(position-getHeadCount());
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


    protected abstract int getDataType(int position);

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
        if (onLoadListenerImp==null)
            return 0;
        return 1;
    }


    //加载失败
    public void loadFail()
    {
        loadView.setState(LoadView.STATE_FAIL);
    }

    //刷新数据
    public void flush(List<T> list)
    {
        if (list!=null&&list.size()>=pageSize)
        {
            loadView.setState(LoadView.STATE_FINISH_TRUE);
        }
        else
        {
            loadView.setState(LoadView.STATE_FINISH_FALSE);
        }
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
                if (list.size()>=pageSize)
                {
                    loadView.setState(LoadView.STATE_FINISH_TRUE);
                }
                else
                {
                    loadView.setState(LoadView.STATE_FINISH_NODATA);
                }
                mData.addAll(list);
                notifyItemRangeInserted(mData.size()-list.size() + getHeadCount(), list.size());
            }
            else
            {
                loadView.setState(LoadView.STATE_FINISH_NODATA);
            }
        }
    }

    /**
     * @param flush 是否刷新
     * @param list
     */
    public void flushOrAddData(boolean flush,List<T> list)
    {
        if (flush)
        {
            flush(list);
        }
        else
        {
            addData(list);
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
        if (header.getParent()==null&&mHeaderLayout==null)
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
    public void addFootView(View foot)
    {
        addFootView(foot,-1);
    }

    public void addFootView(View foot,int index)
    {
        addFootView(foot,index,LinearLayout.VERTICAL);
    }

    public void addFootView(View header, int index, int orientation) {
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
                    return noDataItem(getItemViewType(position))?  gridManager.getSpanCount():1 ;
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
