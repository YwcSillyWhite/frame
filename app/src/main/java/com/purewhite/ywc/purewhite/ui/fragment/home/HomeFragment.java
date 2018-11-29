package com.purewhite.ywc.purewhite.ui.fragment.home;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.databinding.FragmentHomeBinding;
import com.purewhite.ywc.purewhite.mvp.fragment.MvpFragment;
import com.purewhite.ywc.purewhite.ui.fragment.home.adapter.HomePagerAdapter;

import java.util.Arrays;

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
        homePagerAdapter = new HomePagerAdapter(getChildFragmentManager()
                ,        Arrays.asList(getResources().getStringArray(R.array.tab_fragment_home)));
        mDataBinding.viewPager.setAdapter(homePagerAdapter);
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.viewPager);
    }

}
