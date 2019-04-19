package com.purewhite.ywc.purewhite.network.retrofit.request.http;


import com.purewhite.ywc.purewhite.bean.base.BaseBean;
import com.purewhite.ywc.purewhite.bean.main.MainBean;
import com.purewhite.ywc.purewhite.network.retrofit.base.BaseRetrofit;
import com.purewhite.ywc.purewhite.network.rxjava.HttpObserver;
import com.purewhite.ywc.purewhite.network.rxjava.RxSchedulers;

/**
 * @author yuwenchao
 */ //第3方封装利于后期的修改第3方数据
public class HttpUtils {

    private static HttpUtils httpUtils;
    private final HttpService httpService;
    public static HttpUtils newInstance() {
        if (httpUtils==null)
        {
            synchronized (HttpUtils.class)
            {
                if (httpUtils==null)
                {
                    httpUtils=new HttpUtils();
                }
            }
        }
        return httpUtils;
    }


    private HttpUtils() {
        httpService = BaseRetrofit.newInstance().create(HttpService.class);
    }


    public void getFinancil(String content,int pagesize,HttpObserver<BaseBean<MainBean>> httpObserver)
    {
        httpService.getShopList(content,pagesize,1).
                compose(RxSchedulers.<BaseBean<MainBean>>compose()).subscribe(httpObserver);
    }
}
