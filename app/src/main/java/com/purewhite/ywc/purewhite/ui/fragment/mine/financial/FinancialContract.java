package com.purewhite.ywc.purewhite.ui.fragment.mine.financial;

import com.purewhite.ywc.purewhite.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhite.mvp.view.BaseView;
import com.purewhite.ywc.purewhite.ui.fragment.mine.financial.adapter.FinancilAdapter;

/**
 * @author yuwenchao
 */
public class FinancialContract {

    public interface View extends BaseView
    {
        FinancilAdapter getAdapter();
    }

    public interface Presenter extends BasePresenter<View>
    {
        void getData(String content);
    }
}
