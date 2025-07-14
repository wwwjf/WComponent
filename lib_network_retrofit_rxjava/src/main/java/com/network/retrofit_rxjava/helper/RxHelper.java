package com.network.retrofit_rxjava.helper;

import com.common.log.KLog;
import com.network.retrofit_rxjava.error.ApiException;
import com.network.retrofit_rxjava.response.NetworkBaseResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxHelper {
    /**
     * 对结果进行预处理
     *
     * @param <T> 泛型
     * @return 预处理结果
     */
    public static <T> ObservableTransformer<NetworkBaseResponse<T>, T> handleResult() {
        return new ObservableTransformer<>() {
            @Override
            public @NonNull ObservableSource<T> apply(@NonNull Observable<NetworkBaseResponse<T>> upstream) {
                return upstream.flatMap(new Function<NetworkBaseResponse<T>, Observable<T>>() {
                            @Override
                            public Observable<T> apply(NetworkBaseResponse<T> result) {
                                KLog.e(result.getStatus() + "");
                                if (result.getStatus() == 0) {
                                    return createData(result.getData());
                                } else {
                                    return Observable.error(new ApiException(result.getStatus()));
                                }
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> emitter) throws Throwable {
                try {
                    emitter.onNext(data);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }
}
