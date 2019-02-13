package com.purewhite.ywc.purewhite.ui.fragment.coupon;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.purewhite.ywc.purewhite.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhite.mvp.view.BaseView;

import java.util.List;


public class CouponContract {

    public interface View extends BaseView
    {
        void requst(int page,boolean network,int pagesize);

        List<DelegateAdapter.Adapter> getAdapters();
    }

    public interface Presenter extends BasePresenter<View>
    {
        void startData();

        void obtainGoodsData();
    }
}
