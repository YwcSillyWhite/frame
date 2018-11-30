package com.purewhite.ywc.purewhite.ui.fragment.coupon;

import com.purewhite.ywc.purewhite.bean.base.BaseBean;
import com.purewhite.ywc.purewhite.bean.main.MainBean;
import com.purewhite.ywc.purewhite.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhite.network.retrofit.request.http.HttpUtils;
import com.purewhite.ywc.purewhite.network.rxjava.HttpObserver;
import com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter.ThreeAdapter;
import com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter.OneAdapter;
import com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter.FourAdapter;
import com.purewhite.ywc.purewhite.ui.fragment.coupon.adapter.TwoAdapter;

public class CouponPresenter extends PresenterImp<CouponContract.View> implements CouponContract.Presenter {
    @Override
    public void getOneData() {
        HttpUtils.newInstance().getShopCoupon_one("女装",1,new HttpObserver<BaseBean<MainBean>>() {
            @Override
            public void onSuccess(BaseBean<MainBean> mainBeanBaseBean) {
                if (mainBeanBaseBean.getCode()==0&&mainBeanBaseBean.getT()!=null
                        &&mainBeanBaseBean.getT().getData()!=null
                        &&mainBeanBaseBean.getT().getData().size()>0)
                {
                    ((OneAdapter) mView.getListAdapter().get(0)).flush(mainBeanBaseBean.getT().getData());
                }
                else
                {

                }
                getThreeData();
            }

            @Override
            public void onFail(String content) {
                super.onFail(content);
                getThreeData();
            }
        });
    }

    @Override
    public void getThreeData() {
        HttpUtils.newInstance().getShopCoupon_Three("男装",1,new HttpObserver<BaseBean<MainBean>>() {
            @Override
            public void onSuccess(BaseBean<MainBean> mainBeanBaseBean) {
                if (mainBeanBaseBean.getCode()==0&&mainBeanBaseBean.getT()!=null
                        &&mainBeanBaseBean.getT().getData()!=null
                        &&mainBeanBaseBean.getT().getData().size()>0)
                {
                    ((TwoAdapter) mView.getListAdapter().get(1)).setShow(true);
                    ((ThreeAdapter) mView.getListAdapter().get(2)).flush(mainBeanBaseBean.getT().getData());
                }
                else
                {
                    ((TwoAdapter) mView.getListAdapter().get(1)).setShow(false);
                }
                getFoutData(1);
            }

            @Override
            public void onFail(String content) {
                super.onFail(content);
                ((TwoAdapter) mView.getListAdapter().get(1)).setShow(false);
                getFoutData(1);
            }
        });
    }

    @Override
    public void getFoutData(int page) {
        HttpUtils.newInstance().getShopCoupon_Four("内衣",page,new HttpObserver<BaseBean<MainBean>>() {
            @Override
            public void onSuccess(BaseBean<MainBean> mainBeanBaseBean) {
                if (mainBeanBaseBean.getCode()==0&&mainBeanBaseBean.getT()!=null
                        &&mainBeanBaseBean.getT().getData()!=null
                        &&mainBeanBaseBean.getT().getData().size()>0)
                {

                    ((FourAdapter) mView.getListAdapter().get(3)).flush(mainBeanBaseBean.getT().getData());
                }
            }

            @Override
            public void onFail(String content) {
                super.onFail(content);
            }
        });
    }
}
