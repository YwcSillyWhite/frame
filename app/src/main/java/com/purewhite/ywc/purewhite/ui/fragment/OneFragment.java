package com.purewhite.ywc.purewhite.ui.fragment;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.databinding.FragmentOneBinding;
import com.purewhite.ywc.purewhite.mvp.fragment.MvpFragment;
import com.purewhite.ywc.purewhite.mvp.presenter.PresenterImp;

/**
 * Created by yuwenchao on 2018/11/19.
 */

public class OneFragment extends MvpFragment<FragmentOneBinding,PresenterImp>{
    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initView() {

    }
}
