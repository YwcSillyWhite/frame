package com.purewhite.ywc.purewhite.ui.fragment.home.child;

import com.purewhite.ywc.purewhite.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhite.mvp.view.BaseView;
import com.purewhite.ywc.purewhite.ui.fragment.home.child.adapter.HomeChildAdapter;

public class HomeChildContract {

    public interface View extends BaseView
    {

        HomeChildAdapter getHomeChildAdapter();

        void loadfinish(boolean flush);
    }

    public interface Presenter extends BasePresenter<View>
    {
        void getShip(String content);
    }
}
