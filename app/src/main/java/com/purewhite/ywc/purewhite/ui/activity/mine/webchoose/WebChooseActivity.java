package com.purewhite.ywc.purewhite.ui.activity.mine.webchoose;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.app.ActivityUtils;
import com.purewhite.ywc.purewhite.config.BundleUtils;
import com.purewhite.ywc.purewhite.config.OnSingleListener;
import com.purewhite.ywc.purewhite.config.TagUtils;
import com.purewhite.ywc.purewhite.databinding.ActivityWebChooseBinding;
import com.purewhite.ywc.purewhite.mvp.activity.MvpActivity;
import com.purewhite.ywc.purewhite.webview.web.WebActivity;

/**
 * @author yuwenchao
 */
public class WebChooseActivity extends MvpActivity<ActivityWebChooseBinding,WebChoosePresenter>
        implements WebChooseContract.View {
    @Override
    protected WebChoosePresenter creartPresenter() {
        return new WebChoosePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_web_choose;
    }

    private String url="https://www.jianshu.com/u/670ead4db963";

    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            switch (v.getId())
            {
                case R.id.action_left:
                    ActivityUtils.newInstance().finish();
                    break;
                case R.id.web_sure:
                    Bundle build = BundleUtils.newInstance()
                            .putString(TagUtils.uri, url)
                            .build();
                    ActivityUtils.newInstance().startActivity(WebActivity.class,build);
                    break;
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId)
            {
                case R.id.web_url_one:
                    url="https://www.jianshu.com/u/670ead4db963";
                    break;
                case R.id.web_url_two:
                    url="https://schwarzkopf.m.tmall.com/?shop_id=67764140&pid=mm_52605298_40122705_45728050201";
//                    url="https://ai.m.taobao.com";
                    break;
                case R.id.web_url_three:
                    url="https://m.iqiyi.com";
                    break;
                case R.id.web_url_four:
                    url="https://m.v.qq.com";
                    break;
            }
        }
    };

    @Override
    protected void initView() {
       mDataBinding.action.actionCenter.setText("webview筛选");
       mDataBinding.action.actionLeft.setOnClickListener(onSingleListener);
       mDataBinding.webSure.setOnClickListener(onSingleListener);
       mDataBinding.webUrlType.setOnCheckedChangeListener(onCheckedChangeListener);
    }
}
