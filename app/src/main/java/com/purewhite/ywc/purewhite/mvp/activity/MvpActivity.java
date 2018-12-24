package com.purewhite.ywc.purewhite.mvp.activity;

import android.content.Context;
import android.databinding.ViewDataBinding;

import com.purewhite.ywc.purewhite.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhite.mvp.view.BaseView;

/**
 *
 * @author yuwenchao
 * @date 2018/11/5
 */

public abstract class MvpActivity<DB extends ViewDataBinding,P extends PresenterImp>
        extends BaseActivity<DB> implements BaseView {

    protected P mPresenter;
    @Override
    public Context getContext() {
        return this;
    }

    protected abstract P creartPresenter();

    @Override
    protected void beforeView() {
        super.beforeView();
        mPresenter= creartPresenter();
        if (mPresenter!=null) {
            mPresenter.addView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null) {
            mPresenter.deleteView();
        }
    }
}
