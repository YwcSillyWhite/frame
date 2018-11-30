package com.purewhite.ywc.purewhite.ui.fragment.coupon;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.vlayout.VlayoutAdapter;
import com.purewhite.ywc.purewhite.adapter.vlayout.VlayoutType;
import com.purewhite.ywc.purewhite.databinding.FragmentCouponBinding;
import com.purewhite.ywc.purewhite.mvp.fragment.MvpFragment;
import com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter.ThreeAdapter;
import com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter.OneAdapter;
import com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter.FourAdapter;
import com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter.TwoAdapter;

import java.util.ArrayList;
import java.util.List;

public class CouponFragment extends MvpFragment<FragmentCouponBinding,CouponPresenter> implements CouponContract.View {

    private VirtualLayoutManager virtualLayoutManager;
    private VlayoutAdapter vlayoutAdapter;
    private List<DelegateAdapter.Adapter> mAdapterList;
    private OneAdapter oneAdapter;
    private TwoAdapter twoAdapter;
    private ThreeAdapter threeAdapter;
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
        //创建vlayout子适配器
        mAdapterList = new ArrayList<>();

        oneAdapter = new OneAdapter();
        mAdapterList.add(oneAdapter);

        twoAdapter = new TwoAdapter();
        mAdapterList.add(twoAdapter);


        threeAdapter = new ThreeAdapter();
        mAdapterList.add(threeAdapter);

        fourAdapter = new FourAdapter();
        mAdapterList.add(fourAdapter);



        //把子适配的集合加入vlayout的适配器
        vlayoutAdapter.setAdapters(mAdapterList);
        //把vlayout的适配器加入recycler
        mDataBinding.recyclerView.setAdapter(vlayoutAdapter);


    }

    @Override
    public List<DelegateAdapter.Adapter> getListAdapter() {
        return mAdapterList;
    }
}
