package com.purewhite.ywc.purewhite.ui.activity.guide;

import android.content.pm.PackageManager;
import android.view.View;
import android.widget.ImageView;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.app.ActivityUtils;
import com.purewhite.ywc.purewhite.config.LogUtils;
import com.purewhite.ywc.purewhite.config.click.OnSingleListener;
import com.purewhite.ywc.purewhite.config.permisson.PermissonUtils;
import com.purewhite.ywc.purewhite.databinding.ActivityGuideBinding;
import com.purewhite.ywc.purewhite.mvp.activity.MvpActivity;
import com.purewhite.ywc.purewhite.ui.activity.main.MainActivity;
import com.purewhite.ywc.purewhite.ui.adapter.GuidePagerAdapter;
import com.purewhite.ywc.purewhite.view.pagerview.AimaTransforme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author yuwenchao
 * @date 2018/11/6
 */

public class GuideActivity extends MvpActivity<ActivityGuideBinding,GuidePresenter>
        implements GuideContract.View{
    private Integer img[]={R.mipmap.img_welcome_1,R.mipmap.img_welcome_2,
            R.mipmap.img_welcome_3,R.mipmap.img_welcome_4};


    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {

            ActivityUtils.startActivity(MainActivity.class);
            finish();

        }
    };

    @Override
    protected GuidePresenter creartPresenter() {
        return new GuidePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        List<View> list=new ArrayList<>();
        for (int i = 0; i < img.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(img[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            list.add(imageView);
            if (i==img.length-1)
                imageView.setOnClickListener(onSingleListener);
        }
        GuidePagerAdapter guidePagerAdapter = new GuidePagerAdapter(Arrays.asList(img),this);
        mDataBinding.guideViewpager.setAdapter(guidePagerAdapter);
        mDataBinding.guideViewpager.setPageTransformer(false, new AimaTransforme());


        PermissonUtils.startPackageInstalls(this,1);

    }
}
