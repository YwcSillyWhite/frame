package com.purewhite.ywc.purewhite.ui.fragment.home.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.purewhite.ywc.purewhite.adapter.pagerview.BaseFragmentStateAdapter;
import com.purewhite.ywc.purewhite.config.TagUtils;
import com.purewhite.ywc.purewhite.ui.fragment.home.child.HomeChildFragment;

import java.util.List;

public class HomePagerAdapter extends BaseFragmentStateAdapter<String> {

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public HomePagerAdapter(FragmentManager fm, List<String> data) {
        super(fm, data);
    }

    @Override
    protected Fragment getFragment(int position) {
        HomeChildFragment homeChildFragment=new HomeChildFragment();
        Bundle bundle=new Bundle();
        bundle.putString(TagUtils.fragmentString,mDatas.get(position));
        homeChildFragment.setArguments(bundle);
        return homeChildFragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mDatas!=null&&mDatas.size()>position?mDatas.get(position):super.getPageTitle(position);
    }
}
