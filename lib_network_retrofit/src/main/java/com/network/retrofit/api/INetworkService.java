package com.network.retrofit.api;

import com.network.retrofit.response.NetworkBaseResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface INetworkService {

    /**
     * 指定分类数据视频列表
     * @return
     */
    @POST("index_categoryList")
    Call<NetworkBaseResponse> movieList(@Query("type") String type);

    /**
     * 单个视频详细信息
     * @param id
     * @return
     */
    @POST("index_mediaDetail")
    Call<NetworkBaseResponse> mediaDetail(@Query("id") String id);

}