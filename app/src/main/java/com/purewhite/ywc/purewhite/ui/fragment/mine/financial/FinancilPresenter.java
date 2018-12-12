package com.purewhite.ywc.purewhite.ui.fragment.mine.financial;

import com.purewhite.ywc.purewhite.bean.base.BaseBean;
import com.purewhite.ywc.purewhite.bean.main.MainBean;
import com.purewhite.ywc.purewhite.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhite.network.retrofit.request.http.HttpUtils;
import com.purewhite.ywc.purewhite.network.rxjava.HttpObserver;

/**
 * @author yuwenchao
 */
public class FinancilPresenter extends PresenterImp<FinancilContract.View>
        implements FinancilContract.Presenter {

    @Override
    public void getData(String content) {
        HttpUtils.newInstance().getFinancil(content, new HttpObserver<BaseBean<MainBean>>() {
            @Override
            public void onSuccess(BaseBean<MainBean> mainBeanBaseBean) {
                if (mainBeanBaseBean.getT()!=null&&mainBeanBaseBean.getT().getData()!=null
                        &&!mainBeanBaseBean.getT().getData().isEmpty())
                {
                    mView.getAdapter().flush(mainBeanBaseBean.getT().getData());
                }
            }
        });
    }
}
