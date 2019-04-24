package com.purewhite.ywc.purewhite.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.purewhite.ywc.purewhite.adapter.pagerview.BasePagerAdapter;
import com.purewhite.ywc.purewhite.app.ActivityUtils;
import com.purewhite.ywc.purewhite.config.click.OnSingleListener;
import com.purewhite.ywc.purewhite.ui.activity.main.MainActivity;

import java.util.List;

public class GuidePagerAdapter extends BasePagerAdapter<Integer> {

    private Context context;
    public GuidePagerAdapter(List<Integer> data, Context context) {
        super(data);
        this.context=context;
    }

    @Override
    protected View obtainView(int position) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(obtainT(position));
        if (position==getCount()-1)
        {
           imageView.setOnClickListener(new OnSingleListener() {
               @Override
               public void onSingleClick(View v) {
                   ActivityUtils.startActivity(MainActivity.class);
                   if (context instanceof Activity)
                   {
                       ActivityUtils.finish();
                   }
               }
           });
        }
        return imageView;
    }
}
