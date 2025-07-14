package com.network.retrofit_rxjava_two.helper;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 调度类
 */
public class RxTransformerHelper {

    public static <T> ObservableTransformer<T, T> observableIO2Main() {
        return upstream -> upstream.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }
}