package com.purewhite.ywc.purewhite.ui.activity.home.choose;

import android.view.View;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.bean.main.OnSingleListener;
import com.purewhite.ywc.purewhite.databinding.ActivityChooseBinding;
import com.purewhite.ywc.purewhite.mvp.activity.MvpActivity;

public class ChooseActivity extends MvpActivity<ActivityChooseBinding,ChoosePresenter>
        implements ChoosContract.View {

    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {

        }
    };
    @Override
    protected ChoosePresenter creartPresenter() {
        return new ChoosePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_choose;
    }

    @Override
    protected void initView() {
        mDataBinding.action.actionLeft.setOnClickListener(onSingleListener);
        mDataBinding.action.actionCenter.setText("选择");
    }
}
