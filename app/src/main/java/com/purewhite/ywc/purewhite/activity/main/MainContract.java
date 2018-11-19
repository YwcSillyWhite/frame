package com.purewhite.ywc.purewhite.activity.main;

import com.purewhite.ywc.purewhite.activity.main.adapter.MainAdapter;
import com.purewhite.ywc.purewhite.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhite.mvp.view.BaseView;

/**
 * Created by yuwenchao on 2018/11/17.
 */

public class MainContract {

    interface View extends BaseView
    {
        MainAdapter getAdapter();
    }

    interface Presenter extends BasePresenter<View>
    {
        void getData();
    }
}
