package com.purewhite.ywc.purewhite.ui.fragment.home.child;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.callback.OnFullListener;
import com.purewhite.ywc.purewhite.adapter.callback.OnItemListener;
import com.purewhite.ywc.purewhite.adapter.callback.OnLoadListener;
import com.purewhite.ywc.purewhite.adapter.callback.OnLoadListenerImp;
import com.purewhite.ywc.purewhite.adapter.fullview.FullView;
import com.purewhite.ywc.purewhite.adapter.ptr.callback.OnPtrListener;
import com.purewhite.ywc.purewhite.adapter.recyclerview.top.ScrollTopListener;
import com.purewhite.ywc.purewhite.config.TagUtils;
import com.purewhite.ywc.purewhite.databinding.FragmentHomeChildBinding;
import com.purewhite.ywc.purewhite.mvp.fragment.MvpFragment;
import com.purewhite.ywc.purewhite.ui.adapter.GoodsListAdapter;


/**
 * @author yuwenchao
 */
public class HomeChildFragment extends MvpFragment<FragmentHomeChildBinding,HomeChildPresenter>
        implements HomeChildContract.View {

    private OnPtrListener onPtrListener=new OnPtrListener() {
        @Override
        public void pullDown() {
            mPresenter.initPage();
            mPresenter.requestData();
        }
    };

    private OnLoadListener onLoadListener=new OnLoadListenerImp() {
        @Override
        public void pullUp() {
            mPresenter.autoPage();
            mPresenter.requestData();
        }

        @Override
        public void loadAgain() {
            mPresenter.requestData();
        }
    };

    private OnFullListener onFullListener=new OnFullListener() {
        @Override
        public void again() {
            mPresenter.requestData();
        }
    };

    private OnItemListener onItemListener=new OnItemListener() {
        @Override
        public void OnClick(RecyclerView.Adapter adapter, View view, int position, boolean itemView) {

        }
    };


    private GoodsListAdapter goodsListAdapter;
    private ScrollTopListener scrollTopListener;

    @Override
    protected HomeChildPresenter creartPresenter() {
        return new HomeChildPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_child;
    }

    @Override
    protected void initView() {
        int position = getArguments().getInt(TagUtils.fragmentPosition,0);
        mPresenter.setPosition(position);
        mDataBinding.ptrFrame.setOnPtrListener(onPtrListener);
        initRecycler();
    }

    @Override
    protected void soleLoad() {
        super.soleLoad();
        mPresenter.requestData();
    }


    private void initRecycler() {
        goodsListAdapter = new GoodsListAdapter();
        //网络异常点击刷新
        goodsListAdapter.setOnFullListener(onFullListener);
        //初始加载状态
        goodsListAdapter.setFullState(FullView.LODA,false);
        //滑动监听
        goodsListAdapter.setOnLoadListener(onLoadListener);
        //点击监听
        goodsListAdapter.setOnItemListener(onItemListener);
        //长度10
        goodsListAdapter.setPageSize(10);

        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        scrollTopListener = new ScrollTopListener(mDataBinding.imgTop);
        scrollTopListener.setSlideLoad(true);
        mDataBinding.recyclerView.addOnScrollListener(scrollTopListener);
        mDataBinding.recyclerView.setAdapter(goodsListAdapter);
    }


    @Override
    public GoodsListAdapter getAdapter() {
        return goodsListAdapter;
    }

    @Override
    public void responseData(int pageSize) {
        mDataBinding.ptrFrame.refreshComplete(pageSize);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        scrollTopListener.release();
    }
}
