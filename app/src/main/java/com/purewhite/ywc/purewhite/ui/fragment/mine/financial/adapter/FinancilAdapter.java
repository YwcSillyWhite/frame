package com.purewhite.ywc.purewhite.ui.fragment.mine.financial.adapter;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.adapter.recyclerview.BindTypeAdapter;
import com.purewhite.ywc.purewhite.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhite.bean.main.MainBean;
import com.purewhite.ywc.purewhite.databinding.AdapterFragmentFinancilBinding;
import com.purewhite.ywc.purewhite.network.imageload.ImageLoader;

/**
 * @author yuwenchao
 */
public class FinancilAdapter extends BindTypeAdapter<MainBean.DataBean> {

    public FinancilAdapter() {
        addLayout(R.layout.adapter_fragment_financil);
    }

    @Override
    protected void onData(BindHolder holder, int position, MainBean.DataBean dataBean) {
        AdapterFragmentFinancilBinding binding = (AdapterFragmentFinancilBinding) holder.getViewDataBinding();

        if (dataBean!=null)
        {
            ImageLoader.newInstance().initCircle(binding.img,dataBean.getItem_pic());
            binding.text.setText(dataBean.getItem_title());
        }
        else
        {
            ImageLoader.newInstance().initCircle(binding.img,R.mipmap.ic_launcher);
            binding.text.setText("占未激活");
        }
    }

//    @Override
//    public int getDataCount() {
//        return super.getDataCount()==0?16:super.getDataCount();
//    }

}
