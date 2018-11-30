package com.purewhite.ywc.purewhite.adapter.recyclerview.bean;

/**
 *
 * @author yuwenchao
 * @date 2018/11/17
 */

public class BindBeanImp implements BindBean{
    private int beanType=0;

    public void setBeanType(int type) {
        this.beanType = type;
    }

    @Override
    public int getBeanType() {
        return beanType;
    }
}
