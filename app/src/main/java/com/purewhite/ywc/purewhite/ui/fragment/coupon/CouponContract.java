package com.purewhite.ywc.purewhite.ui.fragment.coupon;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.purewhite.ywc.purewhite.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhite.mvp.view.BaseView;

import java.util.List;

public class CouponContract {

    public interface View extends BaseView
    {
        List<DelegateAdapter.Adapter> getListAdapter();
    }

    public interface Presenter extends BasePresenter<View>
    {
        void getOneData();

        void getThreeData();

        void getFoutData(int page);
    }
}
