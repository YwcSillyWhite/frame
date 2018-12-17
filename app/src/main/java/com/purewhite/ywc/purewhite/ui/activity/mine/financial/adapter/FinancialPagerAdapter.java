package com.purewhite.ywc.purewhite.ui.activity.mine.financial.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.purewhite.ywc.purewhite.adapter.pagerview.BaseFragmentAdapter;
import com.purewhite.ywc.purewhite.ui.fragment.mine.financial.FinancialFragment;

import java.util.List;

public class FinancialPagerAdapter extends BaseFragmentAdapter<String> {

    public FinancialPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private ViewPager viewPager;

    public FinancialPagerAdapter(FragmentManager fm, List<String> data) {
        super(fm, data);
    }

    @Override
    protected Fragment getFragment(int position) {
        FinancialFragment fragment = new FinancialFragment();
        Bundle bundle=new Bundle();
        bundle.putString("title",mDatas.get(position));
        bundle.putInt("position",position);
        fragment.setArguments(bundle);
        return fragment;
    }
}
