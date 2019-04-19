package com.purewhite.ywc.purewhite.adapter.fullview;

import android.view.View;

import com.purewhite.ywc.purewhite.R;
import com.purewhite.ywc.purewhite.bean.main.OnSingleListener;
import com.purewhite.ywc.purewhite.config.NetWorkUtils;

public class FullViewImp extends FullView{

    @Override
    public int getLayoutId() {
        return R.layout.fullview;
    }

    @Override
    int getLoadId() {
        return R.id.load;
    }

    @Override
    int getNetworkId() {
        return R.id.network;
    }

    @Override
    int getDataId() {
        return R.id.data;
    }


    @Override
    public void setItemView(View itemView) {
        super.setItemView(itemView);
        if (onFullListener!=null)
        {
            itemView.findViewById(R.id.network_again).setOnClickListener(new OnSingleListener() {
                @Override
                public void onSingleClick(View v) {
                    if (onFullListener!=null&&NetWorkUtils.isConnected())
                    {
                        setFullState(LODA,true);
                        onFullListener.again();
                    }
                }
            });
        }

    }
}
