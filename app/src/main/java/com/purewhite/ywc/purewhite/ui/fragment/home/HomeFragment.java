package com.purewhite.ywc.purewhite.ui.fragment.home;

import android.view.View;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.config.OnSingleListener;
import com.purewhite.ywc.purewhite.databinding.FragmentHomeBinding;
import com.purewhite.ywc.purewhite.mvp.fragment.MvpFragment;
import com.purewhite.ywc.purewhite.ui.fragment.home.adapter.HomePagerAdapter;
import com.purewhite.ywc.purewhite.view.dialog.MainDialog;

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

    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            switch (v.getId())
            {
                case R.id.choose:
                    new MainDialog(getContext()).show();
                    break;
            }
        }
    };

    @Override
    protected void initView() {
        List<String> stringList = Arrays.asList(getResources().getStringArray(R.array.tab_fragment_home));
        homePagerAdapter = new HomePagerAdapter(getChildFragmentManager(),stringList);
        mDataBinding.viewPager.setAdapter(homePagerAdapter);
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.viewPager);
        mDataBinding.viewPager.setOffscreenPageLimit(stringList.size());
        mDataBinding.choose.setOnClickListener(onSingleListener);
    }


    @Override
    protected void showLoad() {
        super.showLoad();

    }
}
