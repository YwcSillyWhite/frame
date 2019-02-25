package com.purewhite.ywc.purewhite.ui.fragment.home.child;

import com.purewhite.ywc.purewhite.bean.GoodsListBean;
import com.purewhite.ywc.purewhite.bean.base.BaseBean;
import com.purewhite.ywc.purewhite.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhite.network.retrofit.base.BaseRetrofit;
import com.purewhite.ywc.purewhite.network.retrofit.request.http.HttpService;
import com.purewhite.ywc.purewhite.network.rxjava.HttpObserver;
import com.purewhite.ywc.purewhite.network.rxjava.RxSchedulers;

import java.util.List;


/**
 * @author yuwenchao
 */
public class HomeChildPresenter extends PresenterImp<HomeChildContract.View> implements HomeChildContract.Presenter {

    private int position;
    public void setPosition(int position) {
        this.position = position;
    }

    private long min_id;
    @Override
    public void requestData() {
        final int page = getPage();
        if (page==1)
        {
            min_id=1;
        }
        BaseRetrofit.newInstance().create(HttpService.class)
                .obtainGoods(position,10,min_id)
                .compose(RxSchedulers.<BaseBean<List<GoodsListBean>>>compose())
                .subscribe(new HttpObserver<BaseBean<List<GoodsListBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<GoodsListBean>> baseBean) {
                        if (baseBean.getCode()==1&&baseBean.getT()!=null
                                &&baseBean.getT().size()>0)
                        {
                            min_id=baseBean.getMin_id();
                            mView.getAdapter().refreshComplete(true,page,baseBean.getT());
                        }
                        mView.responseData(page);

                    }

                    @Override
                    public void onFail(String content) {
                        super.onFail(content);
                        mView.responseData(page);
                        mView.getAdapter().refreshComplete(false,page,null);
                    }
                });
    }
}
