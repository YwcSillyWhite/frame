package com.purewhite.ywc.purewhite.mvp.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.purewhite.ywc.purewhite.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhite.mvp.view.BaseView;

/**
 * Created by yuwenchao on 2018/11/14.
 */

public abstract class MvpFragment<DB extends ViewDataBinding,P extends PresenterImp>
        extends BaseFragment<DB> implements BaseView{

    private P mPresenter;

    protected abstract P creartPresenter();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=creartPresenter();
        if (mPresenter!=null)
            mPresenter.addView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null)
            mPresenter.deleteView();
    }
}
