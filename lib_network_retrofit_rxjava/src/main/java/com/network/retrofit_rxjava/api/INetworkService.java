package com.network.retrofit_rxjava.api;


import com.network.retrofit_rxjava.api.data.MovieBean;
import com.network.retrofit_rxjava.response.NetworkBaseResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface INetworkService {

    /**
     * 指定分类数据视频列表
     * @return
     */
    @POST("index_categoryList")
    Observable<List<MovieBean>> movieList(@Query("type") String type);

    /**
     * 单个视频详细信息
     * @param id
     * @return
     */
    @POST("index_mediaDetail")
    Observable<MovieBean> mediaDetail(@Query("id") String id);

}