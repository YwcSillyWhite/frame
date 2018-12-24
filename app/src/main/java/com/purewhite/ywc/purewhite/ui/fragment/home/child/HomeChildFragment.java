package com.purewhite.ywc.purewhite.ui.fragment.home.child;

import android.support.v7.widget.GridLayoutManager;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.callback.OnFullListener;
import com.purewhite.ywc.purewhite.adapter.callback.OnLoadListener;
import com.purewhite.ywc.purewhite.adapter.fullview.FullView;
import com.purewhite.ywc.purewhite.adapter.callback.OnLoadListenerImp;
import com.purewhite.ywc.purewhite.adapter.fullview.FullViewImp;
import com.purewhite.ywc.purewhite.config.TagUtils;
import com.purewhite.ywc.purewhite.databinding.FragmentHomeChildBinding;
import com.purewhite.ywc.purewhite.mvp.fragment.MvpFragment;
import com.purewhite.ywc.purewhite.ptr.io.PtrCallBack;
import com.purewhite.ywc.purewhite.ui.fragment.home.child.adapter.HomeChildAdapter;
import com.purewhite.ywc.purewhite.view.recyclerview.top.ScrollTopHelp;
import com.purewhite.ywc.purewhite.view.recyclerview.top.ScrollTopListener;


/**
 * @author yuwenchao
 */
public class HomeChildFragment extends MvpFragment<FragmentHomeChildBinding,HomeChildPresenter>
        implements HomeChildContract.View {

    private PtrCallBack ptrCallBack=new PtrCallBack() {
        @Override
        public void onPullDown() {
            mPresenter.initPage();
            mPresenter.getShip(request_contet);
        }
    };

    private OnLoadListener onLoadListener=new OnLoadListenerImp() {
        @Override
        public void pullUp() {
            mPresenter.autoPage();
            mPresenter.getShip(request_contet);
        }

        @Override
        public void loadAgain() {
            mPresenter.getShip(request_contet);
        }
    };

    private OnFullListener onFullListener=new OnFullListener() {
        @Override
        public void again() {
            mPresenter.getShip(request_contet);
        }
    };

    private HomeChildAdapter homeChildAdapter;
    private String request_contet;
    private ScrollTopHelp scrollTopHelp;

    @Override
    protected HomeChildPresenter creartPresenter() {
        return new HomeChildPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_child;
    }

    @Override
    protected void initView() {
        scrollTopHelp = new ScrollTopHelp(mDataBinding.imgTop);
        scrollTopHelp.setRecycClick(mDataBinding.recyclerView);

        request_contet = getArguments().getString(TagUtils.fragmentString);
        mDataBinding.ptrFrame.setPtrHandler(ptrCallBack);
        initRecycler();
    }

    @Override
    protected void soleLoad() {
        super.soleLoad();
        mPresenter.getShip(request_contet);
    }

    @Override
    protected void showLoad() {
        super.showLoad();

    }

    private void initRecycler() {
        homeChildAdapter = new HomeChildAdapter();
        //设置开始fullview加载状态
        FullViewImp fullView = (FullViewImp) homeChildAdapter.getFullView();
        fullView.setFullState(FullView.LODA,false);
        fullView.setOnFullListener(onFullListener);
        //加入加载监听
        homeChildAdapter.setOnLoadListener(onLoadListener);
        //加载的最大条数
        homeChildAdapter.setPageSize(20);

        mDataBinding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(),6));
        //加入加载更多回掉，如果没有就不能家宅更多
        mDataBinding.recyclerView.addOnScrollListener(new ScrollTopListener(scrollTopHelp));
//        mDataBinding.recyclerView.addItemDecoration(new OneDecoration(SizeUtils.dip2px(10f),2));
        mDataBinding.recyclerView.setAdapter(homeChildAdapter);
    }

    @Override
    public HomeChildAdapter getHomeChildAdapter() {
        return homeChildAdapter;
    }

    @Override
    public void loadfinish(boolean flush) {
        if (flush)
        {
            if(!mDataBinding.ptrFrame.isEnabled())
            {
                mDataBinding.ptrFrame.setEnabled(true);
            }
            mDataBinding.ptrFrame.refreshComplete();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        scrollTopHelp.release();
    }
}
