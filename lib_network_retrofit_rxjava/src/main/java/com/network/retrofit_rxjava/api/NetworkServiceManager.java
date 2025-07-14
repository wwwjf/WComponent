package com.network.retrofit_rxjava.api;

import com.network.retrofit_rxjava.RetrofitClient;
import com.network.retrofit_rxjava.helper.RxScheduleTransformer;
import com.network.retrofit_rxjava.api.data.MovieBean;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class NetworkServiceManager {

    private static INetworkService networkService = RetrofitClient.getInstance().create(INetworkService.class);

    /**
     * 影片列表
     *
     * @param param
     * @return
     */
    public static Disposable movieList(String param) {
//        Call<NetworkBaseResponse> call = networkService.movieList(param);
//        ServiceHelper.callEntities(call, MovieBean.class, listener);
        return networkService.movieList(param)
                .compose(RxScheduleTransformer.createObservable())
                .subscribe(new Consumer<List<MovieBean>>() {
                    @Override
                    public void accept(List<MovieBean> movieBeans) throws Throwable {

                    }
                }, throwable -> {

                });

    }

    /**
     * 影片详情
     *
     * @param param
     * @return
     */
    public static Observable movieDetail(String param) {
//        Call<NetworkBaseResponse> call = networkService.mediaDetail(param);
//        ServiceHelper.callEntity(call, MovieBean.class, listener);
        return null;

    }


}