package com.purewhite.ywc.purewhite.ui.activity.home.choose;

import android.view.View;
import android.widget.TextView;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.config.OnSingleListener;
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
        mDataBinding.actionBar.findViewById(R.id.action_left).setOnClickListener(onSingleListener);
        ((TextView) mDataBinding.actionBar.findViewById(R.id.action_center)).setText("选择");
    }
}
