package com.purewhite.ywc.purewhite.ui.fragment.mine;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.app.ActivityUtils;
import com.purewhite.ywc.purewhite.config.click.OnSingleListener;
import com.purewhite.ywc.purewhite.config.permisson.PermissonUtils;
import com.purewhite.ywc.purewhite.databinding.FragmentMineBinding;
import com.purewhite.ywc.purewhite.mvp.fragment.MvpFragment;
import com.purewhite.ywc.purewhite.network.imageload.ImageLoader;
import com.purewhite.ywc.purewhite.ui.activity.mine.friend.FriendActivity;
import com.purewhite.ywc.purewhite.ui.activity.mine.seckill.SeckillActivity;
import com.purewhite.ywc.purewhite.ui.activity.mine.webchoose.WebChooseActivity;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

public class MineFragment extends MvpFragment<FragmentMineBinding,MinePresenter> implements MineContract.View {

    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            switch (v.getId())
            {
                case R.id.add_friend:
                    ActivityUtils.startActivity(FriendActivity.class);
                    break;
                case R.id.seckill:
                    ActivityUtils.startActivity(SeckillActivity.class);
                    break;
                case R.id.web_view:
                    ActivityUtils.startActivity(WebChooseActivity.class);
                    break;
                case R.id.qr_code:

//                    ActivityUtils.startActivity(CaptureActivity.this);
                    Intent intent = new Intent(getActivity(), CaptureActivity.class);
                    startActivity(intent);
                    break;
                case R.id.qr_code_zfb:
                    Uri uri = Uri.parse("alipayqr://platformapi/startapp?saId=10000007");
                    Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent1);
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
        mDataBinding.webView.setOnClickListener(onSingleListener);
        mDataBinding.addFriend.setOnClickListener(onSingleListener);
        mDataBinding.seckill.setOnClickListener(onSingleListener);
        mDataBinding.qrCode.setOnClickListener(onSingleListener);
        mDataBinding.qrCodeZfb.setOnClickListener(onSingleListener);
        mPresenter.getCache();

        PermissonUtils.startPermissons(getContext(),this,"android.permission.CAMERA","android.permission.WRITE_CONTACTS");

    }

    @Override
    public void ObtainCache(String content) {
        mDataBinding.mineMSize.setText(content);
    }





}
