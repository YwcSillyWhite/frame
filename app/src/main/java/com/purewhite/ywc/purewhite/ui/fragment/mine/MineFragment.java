package com.purewhite.ywc.purewhite.ui.fragment.mine;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.app.ActivityUtils;
import com.purewhite.ywc.purewhite.app.AppUtils;
import com.purewhite.ywc.purewhite.config.OnSingleListener;
import com.purewhite.ywc.purewhite.config.TagUtils;
import com.purewhite.ywc.purewhite.databinding.FragmentMineBinding;
import com.purewhite.ywc.purewhite.imageload.ImageLoader;
import com.purewhite.ywc.purewhite.mvp.fragment.MvpFragment;
import com.purewhite.ywc.purewhite.ui.activity.mine.friend.FriendActivity;
import com.purewhite.ywc.purewhite.ui.activity.web.WebActivity;

public class MineFragment extends MvpFragment<FragmentMineBinding,MinePresenter> implements MineContract.View {

    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            switch (v.getId())
            {
                case R.id.tiammao_h:
                    Bundle bundle = new Bundle();
                    bundle.putString(TagUtils.uri,"https://temai.m.taobao.com");
                    ActivityUtils.newInstance().startActivity(WebActivity.class,bundle);
                    break;
                case R.id.huli_h:
                    Bundle bundle1 = new Bundle();
                    bundle1.putString(TagUtils.uri,"https://hzcangyu.com/page/vipRecruit.html");
                    ActivityUtils.newInstance().startActivity(WebActivity.class,bundle1);
                    break;
                case R.id.add_friend:
                    ActivityUtils.newInstance().startActivity(FriendActivity.class);
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
        mDataBinding.huliH.setOnClickListener(onSingleListener);
        mDataBinding.addFriend.setOnClickListener(onSingleListener);
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
