package com.purewhite.ywc.purewhite.ui.activity.web;

import com.purewhite.ywc.purewhite.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhite.mvp.view.BaseView;

/**
 * @author yuwenchao
 */
public class WebContract {
    public interface View extends BaseView
    {

    }

    public interface Presener extends BasePresenter<View>
    {

    }
}
