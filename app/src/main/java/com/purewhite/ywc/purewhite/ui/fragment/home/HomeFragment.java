package com.purewhite.ywc.purewhite.ui.fragment.home;

import android.util.Log;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.databinding.FragmentHomeBinding;
import com.purewhite.ywc.purewhite.mvp.fragment.MvpFragment;
import com.purewhite.ywc.purewhite.ui.fragment.home.adapter.HomePagerAdapter;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends MvpFragment<FragmentHomeBinding,HomePresenter> implements HomeContract.View {

    private HomePagerAdapter homePagerAdapter;

    @Override
    protected HomePresenter creartPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        List<String> stringList = Arrays.asList(getResources().getStringArray(R.array.tab_fragment_home));
        homePagerAdapter = new HomePagerAdapter(getChildFragmentManager(),stringList);
        mDataBinding.viewPager.setAdapter(homePagerAdapter);
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.viewPager);
        mDataBinding.viewPager.setOffscreenPageLimit(stringList.size());
    }


    @Override
    protected void showLoad() {
        super.showLoad();

    }
}
