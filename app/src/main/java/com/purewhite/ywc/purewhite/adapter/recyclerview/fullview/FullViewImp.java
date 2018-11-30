package com.purewhite.ywc.purewhite.adapter.recyclerview.fullview;

import com.purewhite.ywc.purewhite.R;

public class FullViewImp extends FullView{
    @Override
    public int getLayoutId() {
        return R.layout.fullview;
    }

    @Override
    int getLoadId() {
        return R.id.fullview_load;
    }

    @Override
    int getNetworkId() {
        return R.id.fullview_network;
    }

    @Override
    int getDataId() {
        return R.id.fullview_data;
    }

}
