package com.purewhite.ywc.purewhite.ui.activity.main;

import com.purewhite.ywc.purewhite.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhite.mvp.view.BaseView;

/**
 * @author yuwenchao
 * @date 2018/11/17
 */

public class MainContract {

    interface View extends BaseView
    {

    }

    interface Presenter extends BasePresenter<View>
    {

    }
}
