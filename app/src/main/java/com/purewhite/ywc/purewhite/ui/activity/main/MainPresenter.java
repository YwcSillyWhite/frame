package com.purewhite.ywc.purewhite.ui.activity.main;

import com.purewhite.ywc.purewhite.ui.Bean.activity.main.MainBean;
import com.purewhite.ywc.purewhite.mvp.presenter.PresenterImp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuwenchao on 2018/11/17.
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
}
