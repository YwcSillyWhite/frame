package com.purewhite.ywc.purewhite.mvp.presenter;

import com.purewhite.ywc.purewhite.mvp.view.BaseView;

/**
 * Created by yuwenchao on 2018/11/5.
 */

public interface BasePresenter<V extends BaseView>  {

    void addView(V view);

    void deleteView();
}
