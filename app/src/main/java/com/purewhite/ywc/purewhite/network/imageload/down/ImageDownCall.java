package com.purewhite.ywc.purewhite.network.imageload.down;

/**
 * @author yuwenchao
 * @date
 */
public interface ImageDownCall {
    void onSuccess(String imageAddress);

    void onFail();
}
