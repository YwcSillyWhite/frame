package com.purewhite.ywc.purewhite.ui.activity.mine.financial.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.purewhite.ywc.purewhite.adapter.pagerview.BaseFragmentAdapter;
import com.purewhite.ywc.purewhite.ui.fragment.mine.financial.FinancilFragment;

import java.util.List;

public class FinancialPagerAdapter extends BaseFragmentAdapter<String> {

    public FinancialPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public FinancialPagerAdapter(FragmentManager fm, List<String> data) {
        super(fm, data);
    }

    @Override
    protected Fragment getFragment(int position) {
        FinancilFragment fragment = new FinancilFragment();
        Bundle bundle=new Bundle();
        bundle.putString("title",mDatas.get(position));
        fragment.setArguments(bundle);
        return fragment;
    }
}
