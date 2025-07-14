package com.setting.rxjava;

import com.common.log.KLog;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RetryWithDelay implements Function<Observable<? extends Throwable>, Observable<?>> {

    /**
     * 最大重试次数
     */
    private int maxRetries = 3;

    /**
     * 重试的间隔时间
     */
    private int retryDelayMills = 1000;
    /**
     * 当前重试次数
     */
    private int retryCount = 0;

    public RetryWithDelay() {
    }

    public RetryWithDelay(int maxRetries, int retryDelayMills) {
        this.maxRetries = maxRetries;
        this.retryDelayMills = retryDelayMills;
    }

    @Override
    public Observable<?> apply(Observable<? extends Throwable> observable) {
        return observable.flatMap((Function<Throwable, ObservableSource<?>>) throwable -> {
            if (++retryCount <= maxRetries) {
                KLog.e("get error,将在" + retryDelayMills + "毫秒后进行第" + retryCount + "次重试");
                return Observable.timer(retryDelayMills, TimeUnit.MILLISECONDS);
            }
            return Observable.error(throwable);
        });
    }

}
