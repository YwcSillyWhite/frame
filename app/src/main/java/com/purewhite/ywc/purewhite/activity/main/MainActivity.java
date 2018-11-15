package com.purewhite.ywc.purewhite.activity.main;

import android.os.Bundle;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.databinding.ActivityMainBinding;
import com.purewhite.ywc.purewhite.mvp.activity.MvpActivity;
import com.purewhite.ywc.purewhite.mvp.presenter.PresenterImp;

/**
 * @author yuwenchao
 */
public class MainActivity extends MvpActivity<ActivityMainBinding,PresenterImp> {


    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }


}
