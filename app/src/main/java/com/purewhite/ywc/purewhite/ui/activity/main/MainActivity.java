package com.purewhite.ywc.purewhite.ui.activity.main;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.recyclerview.loadview.io.OnLoadListenerImp;
import com.purewhite.ywc.purewhite.config.ToolUtils;
import com.purewhite.ywc.purewhite.databinding.ActivityMainBinding;
import com.purewhite.ywc.purewhite.mvp.activity.MvpActivity;
import com.purewhite.ywc.purewhite.ptr.io.PtrCallBack;
import com.purewhite.ywc.purewhite.ui.activity.main.adapter.MainAdapter;
import com.purewhite.ywc.purewhite.view.popupwindow.DialogPopup;
import com.purewhite.ywc.purewhite.view.recyclerview.ItemTouchCall;
import com.purewhite.ywc.purewhite.view.recyclerview.LoadOnScrollListener;
import com.purewhite.ywc.purewhite.view.recyclerview.OneDecoration;

/**
 * @author yuwenchao
 */
public class MainActivity extends MvpActivity<ActivityMainBinding,MainPresenter> implements MainContract.View {

    private MainAdapter mainAdapter;
    private DialogPopup dialogPopup;
    private int page=1;
    private PtrCallBack ptrCallBack=new PtrCallBack() {
        @Override
        public void onPullDown() {
            page=1;
            mPresenter.getShip(true,"女装",page);
        }
    };
    private OnLoadListenerImp onLoadListenerImp=new OnLoadListenerImp() {
        //上啦加载
        @Override
        public void onPullUp() {
            page++;
            mPresenter.getShip(false,"女装",page);
        }
        //重新加载
        @Override
        public void loadAgain() {
            mPresenter.getShip(false,"女装",page);
        }
    };

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
        mDataBinding.ptrLayout.setPtrHandler(ptrCallBack);
        initRecycler();
        mPresenter.getShip(true,"女装",page);
    }

    private void initRecycler() {


        mDataBinding.recycler.setLayoutManager(new GridLayoutManager(this,2));
        mDataBinding.recycler.addOnScrollListener(new LoadOnScrollListener());
        mDataBinding.recycler.addItemDecoration(new OneDecoration(ToolUtils.dip2px(10f,this),2));


        mainAdapter = new MainAdapter();
        //加入加载监听
        mainAdapter.setOnLoadListenerImp(onLoadListenerImp);
        //加载的最大条数
        mainAdapter.setPageSize(20);
        View foot = LayoutInflater.from(this).inflate(R.layout.foot, mDataBinding.recycler, false);
        //mainAdapter.addHeadView(foot);加入头部
        //加入尾部
        mainAdapter.addFootView(foot);
        mDataBinding.recycler.setAdapter(mainAdapter);
//        //先实例化Callback
//        ItemTouchCall itemTouchCall = new ItemTouchCall();
//        ItemTouchHelper touchHelper = new ItemTouchHelper(itemTouchCall);
//        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
//        touchHelper.attachToRecyclerView(mDataBinding.recycler);

    }


    @Override
    public MainAdapter getAdapter() {
        return mainAdapter;
    }

    @Override
    public void loadfinish(boolean flush) {
        if (flush)
            mDataBinding.ptrLayout.refreshComplete();
    }
}
