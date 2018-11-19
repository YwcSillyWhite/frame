package com.purewhite.ywc.purewhite.activity.main;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.activity.main.adapter.MainAdapter;
import com.purewhite.ywc.purewhite.adapter.recyclerview.BaseAdapter;
import com.purewhite.ywc.purewhite.adapter.recyclerview.io.OnLoadListener;
import com.purewhite.ywc.purewhite.databinding.ActivityMainBinding;
import com.purewhite.ywc.purewhite.mvp.activity.MvpActivity;

/**
 * @author yuwenchao
 */
public class MainActivity extends MvpActivity<ActivityMainBinding,MainPresenter> implements MainContract.View {

    private MainAdapter mainAdapter;

    @Override
    protected MainPresenter creartPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        mainAdapter = new MainAdapter();
        mDataBinding.recycler.setLayoutManager(new GridLayoutManager(this,2));
        mDataBinding.recycler.setAdapter(mainAdapter);
        mainAdapter.setOnLoadListener(new OnLoadListener() {
            @Override
            public void loadback() {

            }
        });
        mPresenter.getData();

        View head = LayoutInflater.from(this).inflate(R.layout.head, mDataBinding.recycler, false);
        mainAdapter.addHeadView(head);

        View foot = LayoutInflater.from(this).inflate(R.layout.foot, mDataBinding.recycler, false);
        mainAdapter.addFootView(foot);

    }


    @Override
    public MainAdapter getAdapter() {
        return mainAdapter;
    }
}
