package com.purewhite.ywc.purewhite.ui.fragment.mine;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.app.ActivityUtils;
import com.purewhite.ywc.purewhite.app.AppUtils;
import com.purewhite.ywc.purewhite.config.OnSingleListener;
import com.purewhite.ywc.purewhite.databinding.FragmentMineBinding;
import com.purewhite.ywc.purewhite.imageload.ImageLoader;
import com.purewhite.ywc.purewhite.mvp.fragment.MvpFragment;
import com.purewhite.ywc.purewhite.ui.activity.web.WebActivity;

public class MineFragment extends MvpFragment<FragmentMineBinding,MinePresenter> implements MineContract.View {

    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            switch (v.getId())
            {
                case R.id.tiammao_h:
                    ActivityUtils.newInstance().startActivity(WebActivity.class);
                    break;
            }
        }
    };

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
        mDataBinding.tiammaoH.setOnClickListener(onSingleListener);
        mPresenter.getCache();
    }

    @Override
    public void ObtainCache(String content) {
        mDataBinding.mineMSize.setText(content);
    }



    @Override
    public Context getContext() {
        return super.getContext()!=null?super.getContext():AppUtils.getContext();
    }


}
