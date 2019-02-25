package com.purewhite.ywc.purewhite.ui.activity.vip.financial;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.bean.main.OnSingleListener;
import com.purewhite.ywc.purewhite.config.SizeUtils;
import com.purewhite.ywc.purewhite.databinding.ActivityFinancialBinding;
import com.purewhite.ywc.purewhite.mvp.activity.MvpActivity;
import com.purewhite.ywc.purewhite.ui.activity.vip.financial.adapter.FinancialPagerAdapter;
import com.purewhite.ywc.purewhite.view.viewpager.WrapViewPager;

import java.util.Arrays;

/**
 * @author yuwenchao
 */
public class FinancialActivity extends MvpActivity<ActivityFinancialBinding,FinancialPresenter>
        implements FinancialContract.View {

    private View view[];
    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            int position=-1;
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
                case R.id.action_left:
                    finish();
                    break;
            }
            if (position>=0&&position!=mDataBinding.viewPager.getCurrentItem())
            {
                startAnimation(v);
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

        mDataBinding.progressBar.startAnim(0,50,0,200);
        int screenWidth = SizeUtils.getScreenWidth();
        int viewWidth = (screenWidth - SizeUtils.dip2px(50)) / 4;
        mDataBinding.action.actionCenter.setText("等级");
        mDataBinding.action.actionLeft.setOnClickListener(onSingleListener);
        view=new View[4];
        view[0]= mDataBinding.vipOne;
        view[1]= mDataBinding.vipTwo;
        view[2]= mDataBinding.vipThree;
        view[3]= mDataBinding.vipFour;

        for (int i = 0; i < view.length; i++) {
            view[i].setOnClickListener(onSingleListener);
            view[i].getLayoutParams().width=viewWidth;
        }
        mDataBinding.vipOne.bringToFront();
        initViewPager();
    }

    private void initViewPager() {
        //viewpager可以设置滑动时间，百度"viewpager滑动速度"，原来是根据反射达到效果
        String[] stringArray = getResources().getStringArray(R.array.financial);
        FinancialPagerAdapter financialPagerAdapter = new FinancialPagerAdapter(getSupportFragmentManager()
                ,Arrays.asList(stringArray));
        mDataBinding.viewPager.setAdapter(financialPagerAdapter);
        mDataBinding.viewPager.setOffscreenPageLimit(3);
    }

    private void startAnimation(final View view)
    {
        mDataBinding.viewPager.bringToFront();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, SizeUtils.dip2px(-10),0);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = animation.getCurrentPlayTime()/animation.getDuration();
                view.setTranslationY((float) animation.getAnimatedValue());
                if (scale>=0.5)
                {
                    view.bringToFront();
                }
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mDataBinding.viewPager.bringToFront();
                view.bringToFront();
            }
        });
        valueAnimator.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDataBinding.viewPager instanceof WrapViewPager)
        {
            mDataBinding.viewPager.removeOnPageChangeImp();
        }
    }
}
