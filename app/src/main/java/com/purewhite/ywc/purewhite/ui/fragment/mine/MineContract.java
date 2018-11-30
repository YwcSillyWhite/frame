package com.purewhite.ywc.purewhite.ui.fragment.mine;

import com.purewhite.ywc.purewhite.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhite.mvp.view.BaseView;

public class MineContract {

    public interface View extends BaseView
    {

    }

    public interface Presenter extends BasePresenter<View>
    {
        void getCache();
    }
}
