package com.purewhite.ywc.purewhite.ui.fragment.mine.financial;

import com.purewhite.ywc.purewhite.bean.base.BaseBean;
import com.purewhite.ywc.purewhite.bean.main.MainBean;
import com.purewhite.ywc.purewhite.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhite.network.retrofit.request.http.HttpUtils;
import com.purewhite.ywc.purewhite.network.rxjava.HttpObserver;

/**
 * @author yuwenchao
 */
public class FinancialPresenter extends PresenterImp<FinancialContract.View>
        implements FinancialContract.Presenter {

    @Override
    public void getData(String content,int position) {
        if (position==0)
            position=12;
        else if (position==1)
            position=16;
        else if (position==2)
            position=12;
        else if (position==3)
            position=20;
        HttpUtils.newInstance().getFinancil(content,position, new HttpObserver<BaseBean<MainBean>>() {
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
