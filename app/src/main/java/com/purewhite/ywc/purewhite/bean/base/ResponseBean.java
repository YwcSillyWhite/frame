package com.purewhite.ywc.purewhite.bean.base;

/**
 * @author yuwenchao
 */
public class ResponseBean<T> {

    private int code;
    private String msg;
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
