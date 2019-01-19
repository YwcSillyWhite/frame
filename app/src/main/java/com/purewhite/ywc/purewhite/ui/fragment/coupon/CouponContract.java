package com.purewhite.ywc.purewhite.ui.fragment.coupon;

import com.purewhite.ywc.purewhite.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhite.mvp.view.BaseView;



public class CouponContract {

    public interface View extends BaseView
    {
        void requst(int page,boolean network,int pagesize);
    }

    public interface Presenter extends BasePresenter<View>
    {
        void getOneData();

        void getThreeData();

        void getFoutData();
    }
}
