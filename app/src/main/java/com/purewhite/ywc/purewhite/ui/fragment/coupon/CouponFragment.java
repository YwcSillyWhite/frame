package com.purewhite.ywc.purewhite.ui.fragment.coupon;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.callback.OnLoadListener;
import com.purewhite.ywc.purewhite.adapter.fullview.FullView;
import com.purewhite.ywc.purewhite.adapter.callback.OnLoadListenerImp;
import com.purewhite.ywc.purewhite.adapter.vlayout.VlayoutAdapter;
import com.purewhite.ywc.purewhite.adapter.vlayout.VlayoutType;
import com.purewhite.ywc.purewhite.databinding.FragmentCouponBinding;
import com.purewhite.ywc.purewhite.mvp.fragment.MvpFragment;

import com.purewhite.ywc.purewhite.ptr.io.OnPtrListener;
import com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter.FiveAdapter;
import com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter.FourAdapter;
import com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter.OneAdapter;
import com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter.ThreeAdapter;
import com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter.TwoAdapter;

public class CouponFragment extends MvpFragment<FragmentCouponBinding,CouponPresenter>
        implements CouponContract.View {

    private VirtualLayoutManager virtualLayoutManager;
    private VlayoutAdapter vlayoutAdapter;
    private SparseArray<DelegateAdapter.Adapter> sparseArray;
    private OneAdapter oneAdapter;
    private TwoAdapter twoAdapter;
    private ThreeAdapter threeAdapter;
    private FiveAdapter fiveAdapter;
    private OnLoadListener onLoadListener=new OnLoadListenerImp() {
        @Override
        public void pullUp() {
            mPresenter.autoPage();
            mPresenter.getFoutData();
        }

        @Override
        public void loadAgain() {
            mPresenter.getFoutData();
        }
    };

    private OnPtrListener onPtrListener=new OnPtrListener() {

        @Override
        public void pullDown() {
            mPresenter.initPage();
            mPresenter.getOneData();
        }
    };
    private FourAdapter fourAdapter;


    @Override
    protected CouponPresenter creartPresenter() {
        return new CouponPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_coupon;
    }

    @Override
    protected void initView() {
        mDataBinding.ptrLayout.setPtrHandler(onPtrListener);
        initRecycler();
//        mPresenter.getOneData();
    }

    private void initRecycler() {
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        recycledViewPool.setMaxRecycledViews(VlayoutType.coupon_one,10);
        recycledViewPool.setMaxRecycledViews(VlayoutType.coupon_two,1);
        recycledViewPool.setMaxRecycledViews(VlayoutType.coupon_three,10);
        recycledViewPool.setMaxRecycledViews(VlayoutType.coupon_five,10);
        //vlayout管理器
        virtualLayoutManager = new VirtualLayoutManager(getContext());
        //加入管理器
        mDataBinding.recyclerView.setLayoutManager(virtualLayoutManager);
        //创建vlayout的适配器
        vlayoutAdapter = new VlayoutAdapter(virtualLayoutManager);
        vlayoutAdapter.setmPagesize(20);
        vlayoutAdapter.setOnLoadListener(onLoadListener);
        vlayoutAdapter.getFullView().setFullState(FullView.LODA,false);
        //把vlayout的适配器加入recycler
        mDataBinding.recyclerView.setAdapter(vlayoutAdapter);
        //创建vlayout子适配器
        sparseArray=new SparseArray<>();

        oneAdapter = new OneAdapter();
        sparseArray.put(VlayoutType.coupon_one,oneAdapter);

        twoAdapter = new TwoAdapter();
        sparseArray.put(VlayoutType.coupon_two,twoAdapter);

        threeAdapter = new ThreeAdapter();
        sparseArray.put(VlayoutType.coupon_three,threeAdapter);

        fourAdapter = new FourAdapter();
        sparseArray.put(VlayoutType.coupon_four,fourAdapter);

        fiveAdapter = new FiveAdapter();
        sparseArray.put(VlayoutType.coupon_five,fiveAdapter);



        //把子适配的集合加入vlayout的适配器
        vlayoutAdapter.setAdapters(sparseArray);



    }

    @Override
    public SparseArray<DelegateAdapter.Adapter> getListAdapter() {
        return sparseArray;
    }

    @Override
    public void requst(int page, boolean network, int pagesize) {
        if (page==1)
        {
            mDataBinding.ptrLayout.setEnabled(true);
            mDataBinding.ptrLayout.refreshComplete();
        }
        vlayoutAdapter.refreshComplete(network,page,pagesize);
    }
}
