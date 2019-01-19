package com.purewhite.ywc.purewhite.ui.activity.mine.friend;

import com.purewhite.ywc.purewhite.bean.base.BaseBean;
import com.purewhite.ywc.purewhite.bean.main.MainBean;
import com.purewhite.ywc.purewhite.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhite.network.retrofit.request.http.HttpUtils;
import com.purewhite.ywc.purewhite.network.rxjava.HttpObserver;

public class FriendPresenter extends PresenterImp<FriendContract.View> implements FriendContract.Presenter {

    private boolean isRun=false;
    @Override
    public void getData() {
        if (!isRun)
        {
            isRun=true;
            autoPage();
            HttpUtils.newInstance().getShop_friend("女装",getPage(),new HttpObserver<BaseBean<MainBean>>() {
                @Override
                public void onSuccess(BaseBean<MainBean> mainBeanBaseBean) {
                    isRun=false;
                    if (mainBeanBaseBean.getCode()==0&&mainBeanBaseBean.getT()!=null
                            &&mainBeanBaseBean.getT().getData()!=null
                            &&mainBeanBaseBean.getT().getData().size()>0)
                    {

                        mView.getAdapter().refreshComplete(true,getPage(),mainBeanBaseBean.getT().getData());
                    }
                    else
                    {
                        mView.getAdapter().refreshComplete(true,getPage(),null);
                    }
                }

                @Override
                public void onFail(String content) {
                    super.onFail(content);
                    //加载失败
                    mView.getAdapter().refreshComplete(false,getPage(),null);

                }
            });
        }

    }
}
