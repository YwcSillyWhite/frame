package com.purewhite.ywc.purewhite.adapter.recyclerview.adapter;

import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.purewhite.ywc.purewhite.adapter.recyclerview.fullview.FullView;
import com.purewhite.ywc.purewhite.adapter.recyclerview.fullview.FullViewImp;
import com.purewhite.ywc.purewhite.adapter.recyclerview.io.OnAllLongListener;
import com.purewhite.ywc.purewhite.adapter.recyclerview.io.OnDataListener;
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
    //加载最多项
    private int pageSize=10;
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
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
            case FULL_ITEM:
                View fullview = LayoutInflater.from(parent.getContext())
                        .inflate(fullView.getLayoutId(), parent, false);
                viewhold=createV(fullview);
                break;
            case HEAD_ITEM:
                viewhold=createV(mHeaderLayout);
                break;
            case FOOT_ITEM:
                viewhold=createV(mFooterLayout);
                break;
            case LOAD_ITEM:
                View loadview = LayoutInflater.from(parent.getContext())
                        .inflate(loadView.getLayoutId(), parent, false);
                viewhold=createV(loadview);
                loadview.setOnClickListener(new OnSingleListener() {
                    @Override
                    public void onSingleClick(View v) {
                        //加载失败，点击重新加载
                        if (loadView.getState()==LoadView.STATE_FAIL)
                        {
                            setLoadState(LoadView.STATE_LOAD,true);
                            onLoadListenerImp.loadAgain();
                        }
                    }
                });
                break;
                default:
                    viewhold = onCreateData(parent,viewType);
                    //设置监听
                    bindDataListener(viewhold);
        }
        bindAllListener(viewhold);
        return viewhold;
    }

    protected abstract V onCreateData(ViewGroup parent, int viewType);



    @Override
    public void onBindViewHolder(V holder, int position) {
        loadMore(position);
        int itemViewType = holder.getItemViewType();
        if (itemViewType==LOAD_ITEM)
        {
            loadView.onBindView(holder);
        }
        else if (itemViewType==FULL_ITEM)
        {
            fullView.onBindView(holder);
        }
        else if (itemViewType!=HEAD_ITEM&&itemViewType!=FOOT_ITEM)
        {
            onData(holder,position,obtainT(position-getHeadCount()));
        }
    }

    //赋值数据
    protected abstract void onData(V holder,int position,T t);

    //判断是不是加载更多
    protected  void loadMore(int position)
    {
        if (getLoadCount()==0||position<getItemCount()-1)
            return;
        //加载结束
        if (loadView.getState()==LoadView.STATE_FINISH) {
            setLoadState(LoadView.STATE_LOAD,false);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    onLoadListenerImp.onPullUp();
                }
            }, 200);
        }
    }





    /************  状态处理   ****************/

    /**
     * 设置加载状态
     * @param statue 状态
     * @param flush  刷新
     * 由于滑动过程中要是刷新数据就会导致崩盘
     */
    private void setLoadState(int statue,boolean flush)
    {
        loadView.setState(statue);
        if (flush)
            notifyItemChanged(getItemCount()-1);
    }

    private void setFullState(int statue)
    {
        fullView.setFullState(statue);
        notifyDataSetChanged();
    }







    /************  数据处理   ****************/
    //添加数据
    public void  refreshComplete(boolean network,int page,List<T> list)
    {
        if (list!=null&&list.size()>0)
        {
            if (page==1)
            {
                flush(list);
            }
            else
            {
                addData(list);
            }
        }
        else
        {
            if (!network)
            {
                if (page==1)
                {
                    setLoadState(LoadView.STATE_FAIL,false);
                    if (getDataCount()==0)
                    {
                        setFullState(FullView.FULL_NETWORK);
                    }
                }
                else
                {
                    setLoadState(LoadView.STATE_FAIL,true);
                }
            }
            else
            {
                if (page==1)
                {
                    setLoadState(LoadView.STATE_REST,false);
                    if (getDataCount()==0)
                    {
                        setFullState(FullView.FULL_DATA);
                    }
                }
                else
                {
                    setLoadState(LoadView.STATE_DATA,true);
                }
            }
        }
    }


    //刷新数据
    public void flush(List<T> list)
    {
        if (list!=null&&list.size()>=pageSize)
        {
            setLoadState(LoadView.STATE_FINISH,true);
        }
        else
        {
            setLoadState(LoadView.STATE_REST,true);
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
                    setLoadState(LoadView.STATE_FINISH,true);
                }
                else
                {
                    setLoadState(LoadView.STATE_DATA,true);
                }
                mData.addAll(list);
                notifyItemRangeInserted(mData.size()-list.size() + getHeadCount(), list.size());
            }
            else
            {
                setLoadState(LoadView.STATE_DATA,true);
            }
        }
    }

    //删除数据
    public void removeFlush(int position)
    {
        if (mData!=null&&mData.size()>position)
        {
            mData.remove(position);
            notifyDataSetChanged();
        }
    }

    //删除数据
    public void remove(int position)
    {
        if (mData!=null&&mData.size()>position)
        {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }



    /************  添加头尾   ****************/
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


    /************  item类型   ****************/
    //全部布局
    private FullView fullView=new FullViewImp();
    //头部
    private LinearLayout mHeaderLayout;
    private List<T> mData;
    //尾部
    private LinearLayout mFooterLayout;
    //加载布局
    private LoadView loadView=new LoadViewImp();
    private final int HEAD_ITEM=10001;
    private final int FOOT_ITEM=10002;
    private final int LOAD_ITEM=10003;
    private final int FULL_ITEM=10004;
    public void setLoadView(LoadView loadView) {
        if (loadView==null)
            throw new UnsupportedOperationException("loadview can not null");
        this.loadView = loadView;
    }
    public FullView getFullView() {
        return fullView;
    }
    public void setFullView(FullView fullView) {
        if (loadView==null)
            throw new UnsupportedOperationException("fullview can not null");
        this.fullView = fullView;
    }
    //full长度
    private int getFullCount()
    {
        if (mData!=null&&mData.size()>0)
            return 0;
        return fullView.isShow()?1:0;
    }
    //head长度
    public int getHeadCount()
    {
        if (mHeaderLayout!=null&&mHeaderLayout.getChildCount()>0)
            return 1;
        return 0;
    }
    //data长度
    public int getDataCount()
    {
        return mData!=null?mData.size():0;
    }
    //foot长度
    private int getFootCount()
    {
        if (mFooterLayout!=null&&mFooterLayout.getChildCount()>0)
            return 1;
        return 0;
    }
    //加载长度
    private int getLoadCount()
    {
        if (onLoadListenerImp==null)
            return 0;
        return 1;
    }

    //判断是不是data数据类型
    public boolean dataType(RecyclerView.ViewHolder viewhold)
    {
        return dataType(viewhold.getItemViewType());
    }

    public boolean dataType(int viewType)
    {
        return viewType!=HEAD_ITEM&&viewType!=FOOT_ITEM
                &&viewType!=LOAD_ITEM&&viewType!=FULL_ITEM;
    }


    //请不要重写这个方法,多布局重写getDataType这个方法
    @Override
    public int getItemViewType(int position) {
        if (getFullCount()>0)
            return FULL_ITEM;
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
        return getFullCount()>0?getFullCount():getHeadCount()+getDataCount()+getFootCount()+getLoadCount();
    }




    /************  监听事件   ****************/
    //数据点击过事件
    private OnDataListener onDataListener;

    public OnDataListener getOnDataListener() {
        return onDataListener;
    }

    public void setOnDataListener(OnDataListener onDataListener) {
        this.onDataListener = onDataListener;
    }

    //所有item的点击事件
    private OnAllLongListener onAllLongListener;
    public void setOnAllLongListener(OnAllLongListener onAllLongListener) {
        this.onAllLongListener = onAllLongListener;
    }

    //滑动监听
    private OnLoadListenerImp onLoadListenerImp;
    public void setOnLoadListenerImp(OnLoadListenerImp onLoadListenerImp) {
        this.onLoadListenerImp = onLoadListenerImp;
    }

    /************  绑定监听   ****************/
    protected void bindDataListener(final V viewhold)
    {
        if (viewhold == null) {
            return;
        }
        final View view = viewhold.itemView;
        if (view == null) {
            return;
        }
        if (getOnDataListener()!=null)
        {
            view.setOnClickListener(new OnSingleListener() {
                @Override
                public void onSingleClick(View v) {
                    int position=viewhold.getLayoutPosition() - getFootCount();
                    getOnDataListener().OnItemCall(BaseAdapter.this,view, position);
                }
            });
        }
    }

    private void bindAllListener(final V viewhold) {
        if (viewhold == null) {
            return;
        }
        final View view = viewhold.itemView;
        if (view == null) {
            return;
        }
        if (onAllLongListener!=null)
            viewhold.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onAllLongListener.OnItemCall(viewhold,BaseAdapter.this);
                    return false;
                }
            });
    }



    /************  解决Layoutmanager影响full，head，foot的宽度bug  ****************/
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
                    return dataType(getItemViewType(position))?1: gridManager.getSpanCount() ;
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
                params.setFullSpan(!dataType(holder));
            }
        }
    }

}
