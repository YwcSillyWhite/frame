package com.purewhite.ywc.purewhite.ui.fragment.mine;

import android.databinding.ViewDataBinding;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.databinding.FragmentMineBinding;
import com.purewhite.ywc.purewhite.imageload.ImageLoader;
import com.purewhite.ywc.purewhite.mvp.fragment.MvpFragment;
import com.purewhite.ywc.purewhite.mvp.presenter.PresenterImp;

public class MineFragment extends MvpFragment<FragmentMineBinding,MinePresenter> implements MineContract.View {
    @Override
    protected MinePresenter creartPresenter() {
        return new MinePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        ImageLoader.newInstance().initHead(mDataBinding.mineHead,R.mipmap.icon_load_error);
    }
}
