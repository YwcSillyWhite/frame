package com.purewhite.ywc.purewhite.ui.fragment.mine.financial;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.databinding.FragmentFinancilBinding;
import com.purewhite.ywc.purewhite.mvp.fragment.MvpFragment;
import com.purewhite.ywc.purewhite.ui.fragment.mine.financial.adapter.FinancilAdapter;

/**
 * @author yuwenchao
 */
public class FinancialFragment extends MvpFragment<FragmentFinancilBinding,FinancialPresenter>
        implements FinancialContract.View {

    private FinancilAdapter financilAdapter;
    private String title;
    private int position;

    @Override
    protected FinancialPresenter creartPresenter() {
        return new FinancialPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_financil;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        title = bundle.getString("title");
        position = bundle.getInt("position");
        initRecycler();
    }

    private void initRecycler() {
        mDataBinding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        financilAdapter = new FinancilAdapter();
        mDataBinding.recyclerView.setAdapter(financilAdapter);
    }

    @Override
    public FinancilAdapter getAdapter() {
        return financilAdapter;
    }


    @Override
    protected void soleLoad() {
        super.soleLoad();
        mPresenter.getData(title,position);
    }

}
