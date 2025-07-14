package com.network.retrofit_rxjava.helper;

import com.network.retrofit_rxjava.error.HttpErrorHandler;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxScheduleTransformer {

    public static <T> ObservableTransformer<T, T> createObservable() {
        return new ObservableTransformer<T, T>() {
            @Override
            public Observable<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .map(getAppErrorHandler())
                        .onErrorResumeNext(new HttpErrorHandler<T>())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    private static <T> Function<T, T> getAppErrorHandler() {
        return t -> t;
    }
}
