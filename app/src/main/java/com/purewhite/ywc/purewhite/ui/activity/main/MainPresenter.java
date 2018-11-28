package com.purewhite.ywc.purewhite.ui.activity.main;

import com.purewhite.ywc.purewhite.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhite.network.retrofit.request.http.HttpUtils2;
import com.purewhite.ywc.purewhite.network.rxjava.HttpObserver;
import com.purewhite.ywc.purewhite.ui.bean.activity.main.MainBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 *
 * @author yuwenchao
 * @date 2018/11/17
 */

public class MainPresenter extends PresenterImp<MainContract.View> implements MainContract.Presenter{

    @Override
    public void getData() {
        List<MainBean> list=new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            MainBean mainBean=new MainBean();
            list.add(mainBean);
        }
        mView.getAdapter().addData(list);
    }

    //淘宝搜索
    @Override
    public void getRequest() {
        Map<String,String> map=new HashMap<>();
        map.put("q","女装");
        HttpUtils2.newInstance("https://suggest.taobao.com/").getRequestBean("sug?code=utf-8", map, new HttpObserver<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody responseBody) {

                    }
                });
    }

}
