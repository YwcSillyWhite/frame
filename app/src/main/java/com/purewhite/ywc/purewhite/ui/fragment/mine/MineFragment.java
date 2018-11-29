package com.purewhite.ywc.purewhite.ui.fragment.mine;

import android.databinding.ViewDataBinding;

import com.purewhite.ywc.purewhite.mvp.fragment.MvpFragment;
import com.purewhite.ywc.purewhite.mvp.presenter.PresenterImp;

public class MineFragment extends MvpFragment<ViewDataBinding,PresenterImp> {
    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void initView() {

    }
}
