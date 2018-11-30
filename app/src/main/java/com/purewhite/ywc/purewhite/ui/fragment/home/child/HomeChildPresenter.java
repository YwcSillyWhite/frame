package com.purewhite.ywc.purewhite.ui.fragment.home.child;

import com.purewhite.ywc.purewhite.bean.base.BaseBean;
import com.purewhite.ywc.purewhite.bean.main.MainBean;
import com.purewhite.ywc.purewhite.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhite.network.retrofit.request.http.HttpUtils;
import com.purewhite.ywc.purewhite.network.rxjava.HttpObserver;

public class HomeChildPresenter extends PresenterImp<HomeChildContract.View> implements HomeChildContract.Presenter {

    @Override
    public void getShip(boolean dialog, final boolean flush, String content, int page) {
        HttpUtils.newInstance().getShop(content,page,new HttpObserver<BaseBean<MainBean>>() {
            @Override
            public void onSuccess(BaseBean<MainBean> mainBeanBaseBean) {
                mView.loadfinish(flush);
                if (mainBeanBaseBean.getCode()==0&&mainBeanBaseBean.getT()!=null
                        &&mainBeanBaseBean.getT().getData()!=null
                        &&mainBeanBaseBean.getT().getData().size()>0)
                {

                    mView.getHomeChildAdapter().flushOrAddData(flush,mainBeanBaseBean.getT().getData());
                }
                else
                {
                    mView.getHomeChildAdapter().requestFail(false,flush);
                }
            }

            @Override
            public void onFail(String content) {
                super.onFail(content);
                mView.loadfinish(flush);
                //加载失败
                mView.getHomeChildAdapter().requestFail(true,flush);

            }
        });
    }


}
