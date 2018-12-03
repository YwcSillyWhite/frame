package com.purewhite.ywc.purewhite.ui.fragment.mine;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.databinding.FragmentMineBinding;
import com.purewhite.ywc.purewhite.imageload.ImageLoader;
import com.purewhite.ywc.purewhite.mvp.fragment.MvpFragment;

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
        mPresenter.getCache();
    }

    @Override
    public void ObtainCache(String content) {
        mDataBinding.mineMSize.setText(content);
    }
}
