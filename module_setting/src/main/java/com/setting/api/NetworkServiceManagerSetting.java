package com.setting.api;

import com.network.retrofit.OnRequestListener;
import com.network.retrofit.RetrofitClient;
import com.network.retrofit.ServiceHelper;
import com.setting.api.data.MovieSettingBean;
import com.network.retrofit.response.NetworkBaseResponse;

import java.util.List;

import retrofit2.Call;

public class NetworkServiceManagerSetting {

    private static INetworkServiceSetting networkService = RetrofitClient.getInstance().create(INetworkServiceSetting.class);

    /**
     * 影片列表
     * @param param
     * @param listener
     * @return
     */
    public static Call movieList(String param, OnRequestListener<List<MovieSettingBean>> listener) {
        Call<NetworkBaseResponse> call = networkService.movieList(param);
        ServiceHelper.callEntities(call, MovieSettingBean.class, listener);
        return call;

    }

    /**
     * 影片详情
     * @param param
     * @param listener
     * @return
     */
    public static Call movieDetail(String param, OnRequestListener<MovieSettingBean> listener) {
        Call<NetworkBaseResponse> call = networkService.mediaDetail(param);
        ServiceHelper.callEntity(call, MovieSettingBean.class, listener);
        return call;

    }


}