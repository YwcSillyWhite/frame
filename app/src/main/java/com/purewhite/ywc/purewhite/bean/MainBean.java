package com.purewhite.ywc.purewhite.bean;

import com.purewhite.ywc.purewhite.bean.base.ResponseBean;

import java.util.List;

public class MainBean{

    private List<List<String>> result;

    public List<List<String>> getResult() {
        return result;
    }

    public void setResult(List<List<String>> result) {
        this.result = result;
    }
}
