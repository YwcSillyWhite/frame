package com.purewhite.ywc.purewhite.ui.activity.main;

import com.purewhite.ywc.purewhite.bean.base.BaseBean;
import com.purewhite.ywc.purewhite.bean.main.MainBean;
import com.purewhite.ywc.purewhite.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhite.network.retrofit.request.http.HttpUtils;
import com.purewhite.ywc.purewhite.network.rxjava.HttpObserver;

/**
 *
 * @author yuwenchao
 * @date 2018/11/17
 */

public class MainPresenter extends PresenterImp<MainContract.View> implements MainContract.Presenter{

    @Override
    public void getShip(final boolean flush, String content, int page) {
        HttpUtils.newInstance().getShop(content,page,new HttpObserver<BaseBean<MainBean>>() {
            @Override
            public void onSuccess(BaseBean<MainBean> mainBeanBaseBean) {
                mView.loadfinish(flush);
                if (mainBeanBaseBean.getCode()==0&&mainBeanBaseBean.getT()!=null
                        &&mainBeanBaseBean.getT().getData()!=null
                        &&mainBeanBaseBean.getT().getData().size()>0)
                {

                    mView.getAdapter().flushOrAddData(flush,mainBeanBaseBean.getT().getData());
                }
            }

            @Override
            public void onFail(String content) {
                super.onFail(content);
                mView.loadfinish(flush);
                //加载失败
                mView.getAdapter().loadFail();

            }
        });
    }
}
