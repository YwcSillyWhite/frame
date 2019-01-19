package com.purewhite.ywc.purewhite.ui.fragment.coupon;

import com.purewhite.ywc.purewhite.bean.base.BaseBean;
import com.purewhite.ywc.purewhite.bean.main.MainBean;
import com.purewhite.ywc.purewhite.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhite.network.retrofit.request.http.HttpUtils;
import com.purewhite.ywc.purewhite.network.rxjava.HttpObserver;

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
//                    ((OneAdapter) mView.getListAdapter().get(VlayoutType.coupon_one)).
//                            flush(mainBeanBaseBean.getT().getData());
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
//                    ((TwoAdapter) mView.getListAdapter().get(VlayoutType.coupon_two)).setShow(true);
//                    ((ThreeAdapter) mView.getListAdapter().get(VlayoutType.coupon_three)).flush(mainBeanBaseBean.getT().getData());
                }
                getFoutData();
            }

            @Override
            public void onFail(String content) {
                super.onFail(content);
                getFoutData();
            }
        });
    }

    @Override
    public void getFoutData() {
        HttpUtils.newInstance().getShopCoupon_Four("内衣",getPage(),new HttpObserver<BaseBean<MainBean>>() {
            @Override
            public void onSuccess(BaseBean<MainBean> mainBeanBaseBean) {
                if (mainBeanBaseBean.getCode()==0&&mainBeanBaseBean.getT()!=null
                        &&mainBeanBaseBean.getT().getData()!=null
                        &&mainBeanBaseBean.getT().getData().size()>0)
                {
//                    ((FourAdapter) mView.getListAdapter().get(VlayoutType.coupon_four)).setShow(true);
//                    ((FiveAdapter) mView.getListAdapter().get(VlayoutType.coupon_five))
//                            .addDataFlush(page,mainBeanBaseBean.getT().getData());
//                    mView.requst(page,true,mainBeanBaseBean.getT().getData().size());
                }
                else
                {
                    mView.requst(getPage(),true,0);
                }

            }

            @Override
            public void onFail(String content) {
                super.onFail(content);
                mView.requst(getPage(),false,0);
            }
        });
    }
}
