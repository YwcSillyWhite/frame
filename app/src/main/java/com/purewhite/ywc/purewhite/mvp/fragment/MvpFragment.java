package com.purewhite.ywc.purewhite.mvp.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.purewhite.ywc.purewhite.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhite.mvp.view.BaseView;

/**
 *
 * @author yuwenchao
 * @date 2018/11/14
 */

public abstract class MvpFragment<DB extends ViewDataBinding,P extends PresenterImp>
        extends BaseFragment<DB> implements BaseView{

    protected P mPresenter;

    protected abstract P creartPresenter();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=creartPresenter();
        if (mPresenter!=null)
            mPresenter.addView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter!=null)
            mPresenter.deleteView();
    }
}
