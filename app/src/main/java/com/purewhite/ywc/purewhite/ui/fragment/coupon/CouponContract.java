package com.purewhite.ywc.purewhite.ui.fragment.coupon;

import android.util.SparseArray;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.purewhite.ywc.purewhite.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhite.mvp.view.BaseView;



public class CouponContract {

    public interface View extends BaseView
    {
        SparseArray<DelegateAdapter.Adapter> getListAdapter();

        void requst(int page,boolean network,int pagesize);
    }

    public interface Presenter extends BasePresenter<View>
    {
        void getOneData();

        void getThreeData();

        void getFoutData();
    }
}
