package com.purewhite.ywc.purewhite.ui.adapter.mine;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.recyclerview.BindAdapter;
import com.purewhite.ywc.purewhite.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhite.bean.SeckillBean;
import com.purewhite.ywc.purewhite.config.SizeUtils;
import com.purewhite.ywc.purewhite.databinding.AdapterActivitySeckillBinding;

import java.util.List;

public class SeckillAdapter extends BindAdapter<SeckillBean> {


    public int seletorPosition=-1;

    public SeckillAdapter(List<SeckillBean> list) {
        super(list);
        addLayout(R.layout.adapter_activity_seckill);
    }

    @Override
    protected void onData(BindHolder holder, int position, SeckillBean seckillBean) {
        if (holder.getViewDataBinding() instanceof AdapterActivitySeckillBinding)
        {
            AdapterActivitySeckillBinding binding= ((AdapterActivitySeckillBinding) holder.getViewDataBinding());
            binding.textContent.setText(seckillBean.getTime());
            binding.itemLayout.setSelected(seletorPosition==position);
        }
    }



    /**
     * 滚动到中间位置
     * @param
     */
    public void seletorFlush(int seletorPosition){
        this.seletorPosition=seletorPosition;
        notifyDataSetChanged();
    }
}
