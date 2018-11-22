package com.purewhite.ywc.purewhite.ui.activity.main;

import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.network.retrofit.request.http.HttpUtils;
import com.purewhite.ywc.purewhite.network.rxjava.HttpObserver;
import com.purewhite.ywc.purewhite.ptr.io.PtrCallBack;
import com.purewhite.ywc.purewhite.ui.activity.main.adapter.MainAdapter;
import com.purewhite.ywc.purewhite.adapter.recyclerview.io.OnLoadListener;
import com.purewhite.ywc.purewhite.databinding.ActivityMainBinding;
import com.purewhite.ywc.purewhite.mvp.activity.MvpActivity;

import okhttp3.ResponseBody;

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

        mDataBinding.ptrLayout.setEnabled(true);
        mDataBinding.ptrLayout.setPtrHandler(new PtrCallBack() {
            @Override
            public void onPullDown() {

            }

            @Override
            public boolean checkCanPullDown() {
                return super.checkCanPullDown();
            }
        });
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
