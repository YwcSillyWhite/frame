package com.purewhite.ywc.purewhite.ui.activity.mine.seckill;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.callback.OnItemListener;
import com.purewhite.ywc.purewhite.bean.SeckillBean;
import com.purewhite.ywc.purewhite.config.SizeUtils;
import com.purewhite.ywc.purewhite.databinding.ActivitySckillBinding;
import com.purewhite.ywc.purewhite.mvp.activity.MvpActivity;
import com.purewhite.ywc.purewhite.ui.adapter.mine.SeckillAdapter;

import java.util.ArrayList;
import java.util.List;

public class SeckillActivity extends MvpActivity<ActivitySckillBinding,SeckillPresenter> implements SeckillContract.View {


    private String title[]={"1","2","3","4","5","6","7"};
    private LinearLayoutManager linearLayoutManager;
    private int screenWidth = SizeUtils.getScreenWidth();
    private OnItemListener onItemListener=new OnItemListener() {
        @Override
        public void OnClick(RecyclerView.Adapter adapter, View view, int position, boolean itemView) {
            if (adapter instanceof SeckillAdapter)
            {
                ((SeckillAdapter) adapter).seletorFlush(position);
                startAnim(view);
            }
        }
    };

    @Override
    protected SeckillPresenter creartPresenter() {
        return new SeckillPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_sckill;
    }

    @Override
    protected void initView() {
        initRecycler();
    }

    private void initRecycler() {
        List<SeckillBean> list=new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            SeckillBean seckillBean = new SeckillBean();
            seckillBean.setTime(title[i]);
            list.add(seckillBean);
        }
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mDataBinding.recyclerView.setLayoutManager(linearLayoutManager);
        final SeckillAdapter seckillAdapter = new SeckillAdapter(list);
        seckillAdapter.seletorFlush(4);
        seckillAdapter.setOnItemListener(onItemListener);
        mDataBinding.recyclerView.setAdapter(seckillAdapter);

        mDataBinding.recyclerView.post(new Runnable() {
            @Override
            public void run() {
                obtain(4);
            }
        });
    }

    private void obtain(int position)
    {
        int firstPosition = linearLayoutManager.findFirstVisibleItemPosition();
        View view = linearLayoutManager.getChildAt(position-firstPosition);
        startAnim(view);
    }

    private void startAnim(View view)
    {
        int left = view.getLeft();
        int width = view.getWidth();
        int zhenshi=(screenWidth-width)/2;
        mDataBinding.recyclerView.scrollBy(left-zhenshi,0);
    }
}
