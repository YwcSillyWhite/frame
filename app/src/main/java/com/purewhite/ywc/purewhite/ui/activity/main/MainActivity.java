package com.purewhite.ywc.purewhite.ui.activity.main;

import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.databinding.ActivityMainBinding;
import com.purewhite.ywc.purewhite.mvp.activity.MvpActivity;
import com.purewhite.ywc.purewhite.mvp.fragment.BaseFragment;
import com.purewhite.ywc.purewhite.ui.fragment.collect.CollectFragment;
import com.purewhite.ywc.purewhite.ui.fragment.coupon.CouponFragment;
import com.purewhite.ywc.purewhite.ui.fragment.home.HomeFragment;
import com.purewhite.ywc.purewhite.ui.fragment.mine.MineFragment;
import com.purewhite.ywc.purewhite.view.BottomLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuwenchao
 */
public class MainActivity extends MvpActivity<ActivityMainBinding,MainPresenter> implements MainContract.View {


    private BottomLayout.OnBottomLayoutChageListener onBottomLayoutChageListener=new BottomLayout.OnBottomLayoutChageListener() {
        @Override
        public void onCheckChange(View view) {
            switch (view.getId())
            {
                case R.id.bottom_one:
                    initFragment(0);
                    break;
                case R.id.bottom_two:
                    initFragment(1);
                    break;
                case R.id.bottom_three:
                    initFragment(2);
                    break;
                case R.id.bottom_four:
                    initFragment(3);
                    break;
            }
        }
    };
    private List<BaseFragment> list=new ArrayList<>();
    private int old_position=-1;
    @Override
    protected MainPresenter creartPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mDataBinding.bottomLayout.addOnBottomLayoutChageListener(onBottomLayoutChageListener);
        list.add(new HomeFragment());
        list.add(new CollectFragment());
        list.add(new CouponFragment());
        list.add(new MineFragment());
        initFragment(0);
        //设置消息数量
        mDataBinding.bottomThree.setMessageNum(15);
    }

    private void initFragment(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //是否加入
        if (list.get(position).isAdded())
        {
            fragmentTransaction.show(list.get(position));
        }
        else
        {
            fragmentTransaction.add(R.id.frame_layout,list.get(position));
        }
        if (old_position>=0&&old_position<list.size())
            fragmentTransaction.hide(list.get(old_position));
        fragmentTransaction.commit();
        old_position=position;
    }




}
