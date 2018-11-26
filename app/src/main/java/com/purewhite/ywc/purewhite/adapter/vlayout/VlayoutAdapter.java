package com.purewhite.ywc.purewhite.adapter.vlayout;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.purewhite.ywc.purewhite.adapter.recyclerview.io.OnLoadListener;
import com.purewhite.ywc.purewhite.adapter.recyclerview.loadview.LoadView;
import com.purewhite.ywc.purewhite.adapter.recyclerview.loadview.LoadViewImp;
import com.purewhite.ywc.purewhite.adapter.recyclerview.viewholder.BaseViewHolder;
import com.purewhite.ywc.purewhite.config.OnSingleListener;

import java.util.List;

public class VlayoutAdapter extends DelegateAdapter
{
    //加载更多load itemtype
    private final int LOAD_VIEW=0x00010001;
    //加载更多监听
    private OnLoadListener onLoadListener;
    private LoadView loadView=new LoadViewImp();
    private Handler handler=new Handler();
    private int mPagesize=10;

    public void setPagesize(int pagesize) {
        this.mPagesize = pagesize;
    }

    public void setLoadView(LoadView loadView) {
        if (loadView!=null)
        {
            this.loadView = loadView;
        }
        else
        {
            throw new UnsupportedOperationException("loadvuew can not null");
        }

    }

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

    //加载更多布局的item
    private int getLoadCount()
    {
        if (onLoadListener==null)
            return 0;
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        int dataSize = getItemCount() - getLoadCount();
        if (position < dataSize) {
            return super.getItemViewType(position);
        } else {
            return LOAD_VIEW;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == LOAD_VIEW)
            return getLoadViewHolder(parent);
        return super.onCreateViewHolder(parent, viewType);
    }

    private RecyclerView.ViewHolder getLoadViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(loadView.getLayoutId(), parent, false);
        BaseViewHolder viewHolder = new BaseViewHolder(view);
        viewHolder.itemView.setOnClickListener(new OnSingleListener() {
            @Override
            public void onSingleClick(View v) {
                //点击加载失败的接口
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
    }

    //判断是否能加载更多
    private void judgeLoadMore(int position) {
        if (getLoadCount()==0||position<getItemCount()-1)
            return;
        if (loadView.getState()==LoadView.STATE_FINISH_TRUE)
        {
            loadView.setState(LoadView.STATE_LOAD);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    onLoadListener.loadback();
                }
            },200);
        }
    }

    /**
     * @param loadfail  加载失败
     * @param pull_up   是否上啦刷新
     * @param pagesize  数据长度
     */
    public void refreshComplete(boolean loadfail,boolean pull_up,int pagesize)
    {
        if (pull_up)
        {
            if (loadfail)
            {
                loadView.setState(LoadView.STATE_FAIL);
            }
            else if (pagesize<mPagesize)
            {
                loadView.setState(LoadView.STATE_FINISH_NODATA);
            }
            else
            {
                loadView.setState(LoadView.STATE_FINISH_TRUE);
            }

        }
        else
        {
            if (loadfail)
            {
                //加载失败
            }
            else if (pagesize<=0)
            {
                //没有数据
            }
            else if (pagesize<mPagesize)
            {
                loadView.setState(LoadView.STATE_FINISH_FALSE);
            }
            else
            {
                loadView.setState(LoadView.STATE_FINISH_TRUE);
            }

        }
    }
}
