package com.network.retrofit.api;

import com.network.retrofit.OnRequestListener;
import com.network.retrofit.RetrofitClient;
import com.network.retrofit.ServiceHelper;
import com.network.retrofit.api.data.MovieBean;
import com.network.retrofit.response.NetworkBaseResponse;


import java.util.List;

import retrofit2.Call;

public class NetworkServiceManager {

    private static INetworkService networkService = RetrofitClient.getInstance().create(INetworkService.class);

    /**
     * 影片列表
     * @param param
     * @param listener
     * @return
     */
    public static Call movieList(String param, OnRequestListener<List<MovieBean>> listener) {
        Call<NetworkBaseResponse> call = networkService.movieList(param);
        ServiceHelper.callEntities(call, MovieBean.class, listener);
        return call;

    }

    /**
     * 影片详情
     * @param param
     * @param listener
     * @return
     */
    public static Call movieDetail(String param, OnRequestListener<MovieBean> listener) {
        Call<NetworkBaseResponse> call = networkService.mediaDetail(param);
        ServiceHelper.callEntity(call, MovieBean.class, listener);
        return call;

    }


}