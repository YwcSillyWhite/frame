package com.purewhite.ywc.purewhite.ui.fragment.coupon;

import com.purewhite.ywc.purewhite.adapter.vlayout.VlayoutType;
import com.purewhite.ywc.purewhite.bean.GoodsListBean;
import com.purewhite.ywc.purewhite.bean.base.BaseBean;
import com.purewhite.ywc.purewhite.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhite.network.retrofit.base.BaseRetrofit;
import com.purewhite.ywc.purewhite.network.retrofit.request.http.HttpService;
import com.purewhite.ywc.purewhite.network.rxjava.HttpObserver;
import com.purewhite.ywc.purewhite.network.rxjava.RxSchedulers;
import com.purewhite.ywc.purewhite.ui.adapter.coupon.CouponFourAdapter;
import com.purewhite.ywc.purewhite.ui.adapter.coupon.CouponOneAdapter;
import com.purewhite.ywc.purewhite.ui.adapter.coupon.CouponThreeAdapter;
import com.purewhite.ywc.purewhite.ui.adapter.coupon.CouponTwoAdapter;

import java.util.List;

public class CouponPresenter extends PresenterImp<CouponContract.View> implements CouponContract.Presenter {

    @Override
    public void startData() {
        obtainOne();
    }


    //获取10大金刚数据
    private void obtainOne() {
        BaseRetrofit.newInstance().create(HttpService.class)
                .obtainGoods(5,10,1)
                .compose(RxSchedulers.<BaseBean<List<GoodsListBean>>>ioToMain())
                .subscribe(new HttpObserver<BaseBean<List<GoodsListBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<GoodsListBean>> baseBean) {
                        if (baseBean.getCode()==1&&baseBean.getT()!=null &&baseBean.getT().size()>0)
                        {
                            ((CouponOneAdapter) mView.getAdapters().get(VlayoutType.coupon_one)).flushShow(true);
                            ((CouponTwoAdapter) mView.getAdapters().get(VlayoutType.coupon_two)).flush(baseBean.getT());
                        }
                        obtainGoodsData();
                    }

                    @Override
                    public void onFail(String content) {
                        super.onFail(content);
                        obtainGoodsData();
                    }
                });
    }

    private long new_page;
    @Override
    public void obtainGoodsData() {
        final int page = getPage();
        if (page==1)
        {
            new_page=1;
        }
        BaseRetrofit.newInstance().create(HttpService.class)
                .obtainGoods(0,10,new_page)
                .compose(RxSchedulers.<BaseBean<List<GoodsListBean>>>ioToMain())
                .subscribe(new HttpObserver<BaseBean<List<GoodsListBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<GoodsListBean>> baseBean) {
                        int size=0;
                        if (baseBean.getCode()==1&&baseBean.getT()!=null &&baseBean.getT().size()>0)
                        {
                            size=baseBean.getT().size();
                            new_page=baseBean.getMin_id();
                            ((CouponThreeAdapter) mView.getAdapters().get(VlayoutType.coupon_three)).flushShow(true);
                            ((CouponFourAdapter) mView.getAdapters().get(VlayoutType.coupon_four))
                                    .addDataFlush(page,baseBean.getT());
                        }
                        mView.requst(page,true,size);
                    }

                    @Override
                    public void onFail(String content) {
                        super.onFail(content);
                        mView.requst(page,false,0);
                    }
                });
    }
}
