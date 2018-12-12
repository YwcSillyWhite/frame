package com.purewhite.ywc.purewhite.ui.activity.mine.financial;

import android.view.View;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.config.OnSingleListener;
import com.purewhite.ywc.purewhite.databinding.ActivityFinancialBinding;
import com.purewhite.ywc.purewhite.mvp.activity.MvpActivity;
import com.purewhite.ywc.purewhite.ui.activity.mine.financial.adapter.FinancialPagerAdapter;

import java.util.Arrays;

/**
 * @author yuwenchao
 */
public class FinancialActivity extends MvpActivity<ActivityFinancialBinding,FinancialPresenter>
        implements FinancialContract.View {

    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            int position=0;
            switch (v.getId())
            {
                case R.id.vip_one:
                    position=0;
                    break;
                case R.id.vip_two:
                    position=1;
                    break;
                case R.id.vip_three:
                    position=2;
                    break;
                case R.id.vip_four:
                    position=3;
                    break;
            }
            if (position!=mDataBinding.viewPager.getCurrentItem())
            {
                mDataBinding.viewPager.setCurrentItem(position);
            }
        }
    };

    @Override
    protected FinancialPresenter creartPresenter() {
        return new FinancialPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_financial;
    }

    @Override
    protected void initView() {
        mDataBinding.vipOne.setOnClickListener(onSingleListener);
        mDataBinding.vipTwo.setOnClickListener(onSingleListener);
        mDataBinding.vipThree.setOnClickListener(onSingleListener);
        mDataBinding.vipFour.setOnClickListener(onSingleListener);
        initViewPager();
    }

    private void initViewPager() {
        String[] stringArray = getResources().getStringArray(R.array.financial);
        FinancialPagerAdapter financialPagerAdapter = new FinancialPagerAdapter(getSupportFragmentManager()
                ,Arrays.asList(stringArray));
        mDataBinding.viewPager.setAdapter(financialPagerAdapter);
    }
}
