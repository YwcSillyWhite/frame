package com.purewhite.ywc.purewhite.network.retrofit.request.http;

import com.purewhite.ywc.purewhite.bean.GoodsListBean;
import com.purewhite.ywc.purewhite.bean.base.BaseBean;
import com.purewhite.ywc.purewhite.bean.main.MainBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
/**
 * @author yuwenchao
 * 需要什么根据自己需求变化
 */
public interface HttpService {

    @GET()
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String,Object> maps);


    @GET()
    Observable<ResponseBody> getResponBody(@Url String url, @QueryMap Map<String,String> maps);

    @FormUrlEncoded
    @POST("goods/querySpecialsale")
    Observable<BaseBean<MainBean>> getShopList(@Field("shoptype") String shoptype
            , @Field("pageSize")int pageSize, @Field("pageNo")int pageNo);


    @GET("itemlist")
    Observable<BaseBean<List<GoodsListBean>>> obtainGoods(@Query("cid")int position, @Query("back")int pageSise, @Query("min_id")long page);

}
