package com.purewhite.ywc.purewhite.bean.base;

import com.google.gson.annotations.SerializedName;

/**
 * @author yuwenchao
 */
public class BaseBean<T> {

    private int code;
    private String msg;
    @SerializedName("returnModel")
    private T t;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
