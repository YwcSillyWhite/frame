package com.purewhite.ywc.purewhite.adapter.recyclerview;

import com.purewhite.ywc.purewhite.adapter.bean.BaseTypeBean;

import java.util.List;

/**
 *
 * @author yuwenchao
 * @date 2018/11/17
 * 使用数据里面的
 */

public abstract class BindTypeAdapter<T extends BaseTypeBean> extends BindAdapter<T>{


    public BindTypeAdapter() {
        super();
    }
    public BindTypeAdapter(List<T> list) {
        super(list);
    }


    @Override
    protected int getDataType(int position) {
        return obtainT(position)!=null?obtainT(position).getTypeBean():0;
    }
}
