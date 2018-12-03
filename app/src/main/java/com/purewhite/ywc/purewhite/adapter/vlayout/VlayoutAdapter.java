package com.purewhite.ywc.purewhite.adapter.vlayout;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.purewhite.ywc.purewhite.adapter.recyclerview.fullview.FullView;
import com.purewhite.ywc.purewhite.adapter.recyclerview.fullview.FullViewImp;
import com.purewhite.ywc.purewhite.adapter.recyclerview.loadview.LoadView;
import com.purewhite.ywc.purewhite.adapter.recyclerview.loadview.LoadViewImp;
import com.purewhite.ywc.purewhite.adapter.recyclerview.loadview.io.OnLoadListenerImp;
import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BaseViewHolder;
import com.purewhite.ywc.purewhite.app.AppUtils;
import com.purewhite.ywc.purewhite.config.OnSingleListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuwenchao
 */
public class VlayoutAdapter extends DelegateAdapter
{
    //加载更多load itemtype
    private final int LOAD_VIEW=0x00010001;
    private final int FULL_VIEW=0x00010002;
    //加载更多监听
    private OnLoadListenerImp onLoadListenerImp;
    public void setOnLoadListenerImp(OnLoadListenerImp onLoadListenerImp) {
        this.onLoadListenerImp = onLoadListenerImp;
    }

    //数据长度
    private int mPagesize=10;
    public void setmPagesize(int mPagesize) {
        this.mPagesize = mPagesize;
    }

    //全屏布局
    private FullView fullView=new FullViewImp();

    public FullView getFullView() {
        return fullView;
    }

    public void setFullView(FullView fullView) {
        if (fullView==null)
            throw new UnsupportedOperationException("fullview can not null");
        this.fullView = fullView;
    }

    //加载布局
    private LoadView loadView=new LoadViewImp();
    public void setLoadView(LoadView loadView) {
        if (loadView==null)
            throw new UnsupportedOperationException("loadview can not null");
        this.loadView = loadView;
    }
    private Handler handler=new Handler();



    public VlayoutAdapter(VirtualLayoutManager layoutManager) {
        super(layoutManager);
    }

    public VlayoutAdapter(VirtualLayoutManager layoutManager, boolean hasConsistItemType) {
        super(layoutManager, hasConsistItemType);
    }


    @Override
    public int getItemCount() {
        return super.getItemCount()+getLoadCount();
    }

    //全局布局长度
    private int getFullCount()
    {
        if (getItemCount()>1)
            return 0;
        return fullView.isShow()?1:0;
    }

    //加载更多布局的item
    private int getLoadCount()
    {
        if (onLoadListenerImp==null)
            return 0;
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (getFullCount()==1)
            return FULL_VIEW;
        if (position<getItemCount()-getLoadCount())
        {
            return getDataViewType(position);
        }
        else{
            return LOAD_VIEW;
        }
    }

    protected int getDataViewType(int position)
    {
        return super.getItemViewType(position);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder=null;
        if (viewType == LOAD_VIEW)
        {
            viewHolder=getLoadViewHolder(parent);
        }
        else if (viewType==FULL_VIEW)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(fullView.getLayoutId(),
                    parent, false);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height=AppUtils.getScreenHeight();
            viewHolder = new BaseViewHolder(view);
        }
        else
        {
            viewHolder = super.onCreateViewHolder(parent, viewType);
        }
        return viewHolder;
    }

    private RecyclerView.ViewHolder getLoadViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(loadView.getLayoutId(), parent, false);
        BaseViewHolder viewHolder = new BaseViewHolder(view);
        viewHolder.itemView.setOnClickListener(new OnSingleListener() {
            @Override
            public void onSingleClick(View v) {
                //点击加载失败的接口
                //加载失败，点击重新加载
                if (loadView.getState()==LoadView.STATE_FAIL)
                {
                    setState(LoadView.STATE_LOAD,true);
                    onLoadListenerImp.loadAgain();
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        judgeLoadMore(position);
        int itemViewType = holder.getItemViewType();
        if (itemViewType == LOAD_VIEW) {
            loadView.onBindView((BaseViewHolder) holder);
        }
        else if (itemViewType==FULL_VIEW)
        {
            fullView.onBindView(((BaseViewHolder) holder));
        }
    }

    //判断是否能加载更多
    private void judgeLoadMore(int position) {
        if (getLoadCount()==0||position<getItemCount()-1)
            return;
        if (loadView.getState()==LoadView.STATE_FINISH_TRUE)
        {
            //滑动刷新会报错
            setState(LoadView.STATE_LOAD,false);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    onLoadListenerImp.onPullUp();
                }
            },200);
        }
    }

    /**
     * @param network_fail  加载失败
     * @param flush   是否上啦刷新
     * @param pagesize  数据长度
     */
    public void refreshComplete(boolean network_fail,boolean flush,int pagesize)
    {
        //如果item的长度等于fullview的长度，并且返回的数据长度等于的0的时候
        if (getItemCount()==getFullCount()||pagesize==0)
        {
            fullView.setFullState(network_fail?FullView.FULL_NETWORK:FullView.FULL_LOAD);
            notifyDataSetChanged();
        }
        if (flush)
        {
            if (pagesize<mPagesize)
            {

                setState(LoadView.STATE_FINISH_FALSE,true);
            }
            else
            {
                setState(LoadView.STATE_FINISH_TRUE,true);
            }
        }
        else
        {
            if (pagesize==0)
            {
                setState(network_fail?LoadView.STATE_FAIL:LoadView.STATE_FINISH_NODATA,true);
            }
            else if (pagesize<mPagesize)
            {
                setState(LoadView.STATE_FINISH_NODATA,true);
            }
            else
            {
                setState(LoadView.STATE_FINISH_TRUE,true);
            }
        }
    }


    private void setState(int statue,boolean flush)
    {
        loadView.setState(statue);
        if (flush)
            notifyItemChanged(getItemCount()-1);
    }

    //使用类型避免因为过多导致适配器混乱
    public void setAdapters(SparseArray<Adapter> sparseArray) {
        List<Adapter> adapters = obtainListAdapter(sparseArray);
        super.setAdapters(adapters);
    }

    private List<Adapter> obtainListAdapter(SparseArray<Adapter> sparseArray)
    {
        List<Adapter> list=new ArrayList<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            list.add(sparseArray.valueAt(i));
        }
        return list;
    }



}
