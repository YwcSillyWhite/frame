package com.purewhite.ywc.purewhite.ui.fragment.home.child;

import com.purewhite.ywc.purewhite.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhite.mvp.view.BaseView;
import com.purewhite.ywc.purewhite.ui.adapter.GoodsListAdapter;

public class HomeChildContract {

    public interface View extends BaseView
    {

        GoodsListAdapter getAdapter();

        void responseData(int pageSize);
    }

    public interface Presenter extends BasePresenter<View>
    {
        void requestData();
    }
}
