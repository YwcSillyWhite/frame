package com.purewhite.ywc.purewhite.ui.fragment.coupon;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.recyclerview.fullview.FullView;
import com.purewhite.ywc.purewhite.adapter.recyclerview.loadview.io.OnLoadListener;
import com.purewhite.ywc.purewhite.adapter.recyclerview.loadview.io.OnLoadListenerImp;
import com.purewhite.ywc.purewhite.adapter.vlayout.VlayoutAdapter;
import com.purewhite.ywc.purewhite.adapter.vlayout.VlayoutType;
import com.purewhite.ywc.purewhite.databinding.FragmentCouponBinding;
import com.purewhite.ywc.purewhite.mvp.fragment.MvpFragment;
import com.purewhite.ywc.purewhite.ptr.io.PtrCallBack;
import com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter.ThreeAdapter;
import com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter.OneAdapter;
import com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter.FourAdapter;
import com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter.TwoAdapter;

import java.util.ArrayList;
import java.util.List;

public class CouponFragment extends MvpFragment<FragmentCouponBinding,CouponPresenter>
        implements CouponContract.View {

    private VirtualLayoutManager virtualLayoutManager;
    private VlayoutAdapter vlayoutAdapter;
    private SparseArray<DelegateAdapter.Adapter> sparseArray;
    private OneAdapter oneAdapter;
    private TwoAdapter twoAdapter;
    private ThreeAdapter threeAdapter;
    private FourAdapter fourAdapter;
    private int page=1;
    private OnLoadListenerImp onLoadListenerImp=new OnLoadListenerImp() {
        @Override
        public void onPullUp() {
            page++;
            mPresenter.getFoutData(page);
        }

        @Override
        public void loadAgain() {
            mPresenter.getFoutData(page);
        }
    };

    private PtrCallBack ptrCallBack=new PtrCallBack() {
        @Override
        public void onPullDown() {
            page=1;
            mPresenter.getFoutData(page);
        }
    };


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
        mDataBinding.ptrLayout.setPtrHandler(ptrCallBack);
        initRecycler();
        mPresenter.getOneData();
    }

    private void initRecycler() {
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        recycledViewPool.setMaxRecycledViews(VlayoutType.coupon_one,10);
        recycledViewPool.setMaxRecycledViews(VlayoutType.coupon_two,1);
        recycledViewPool.setMaxRecycledViews(VlayoutType.coupon_three,10);
        recycledViewPool.setMaxRecycledViews(VlayoutType.coupon_four,10);
        //vlayout管理器
        virtualLayoutManager = new VirtualLayoutManager(getContext());
        //加入管理器
        mDataBinding.recyclerView.setLayoutManager(virtualLayoutManager);
        //创建vlayout的适配器
        vlayoutAdapter = new VlayoutAdapter(virtualLayoutManager);
        vlayoutAdapter.setOnLoadListenerImp(onLoadListenerImp);
        vlayoutAdapter.getFullView().setFullState(FullView.FULL_LOAD);
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



        //把子适配的集合加入vlayout的适配器
        vlayoutAdapter.setAdapters(sparseArray);
        //把vlayout的适配器加入recycler
        mDataBinding.recyclerView.setAdapter(vlayoutAdapter);


    }

    @Override
    public SparseArray<DelegateAdapter.Adapter> getListAdapter() {
        return sparseArray;
    }

    @Override
    public void requst(boolean flush, boolean network, int pagesize) {
        if (flush)
        {
            mDataBinding.ptrLayout.setEnabled(true);
            mDataBinding.ptrLayout.refreshComplete();
        }
        vlayoutAdapter.refreshComplete(network,flush,pagesize);
    }
}
