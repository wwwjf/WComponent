package com.network.retrofit_rxjava_two.error;


import com.common.log.KLog;
import com.common.utils.StringUtil;
import com.google.gson.reflect.TypeToken;
import com.network.retrofit_rxjava_two.helper.GsonHelper;
import com.network.retrofit_rxjava_two.response.BaseResponse5;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.exceptions.UndeliverableException;
import io.reactivex.rxjava3.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class NetworkErrorHandler<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(Throwable throwable) {
        if (throwable instanceof RequestError5) {
            throwable.printStackTrace();
            RequestError5 error = (RequestError5) throwable;
            String message = error.getMessage();
            KLog.e(("code=" + error.getCode() + ",message=" + message));
            return Observable.error(new RequestError5(3001, message));

        } else if (throwable instanceof HttpException) {
            HttpException exception = (HttpException) throwable;
            String msg = exception.getMessage();
            int code = exception.code();
            try {
                ResponseBody responseBody = exception.response().errorBody();
                if (responseBody != null) {
                    BaseResponse5<Object> entity = GsonHelper.getsGson().fromJson(responseBody.string(), new TypeToken<BaseResponse5<Object>>() {
                    }.getType());
                    if (entity != null) {
                        code = entity.getCode();
                        msg = StringUtil.isTrimEmpty(entity.getMsg()) ? msg :StringUtil.valueOf(entity.getMsg());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                msg = exception.getMessage();
            }

            KLog.e(("code=" + code + ",message=" + msg));
            return Observable.error(new RequestError5(code, msg));

        } else if (throwable instanceof SocketTimeoutException || throwable instanceof TimeoutException) {

            return Observable.error(new RequestError5(3002, "timeout"));

        } else if (throwable instanceof ConnectException || throwable instanceof UnknownHostException) {

            return Observable.error(new RequestError5(3003, "notNetwork"));

        } else if (throwable instanceof UndeliverableException) {

            return Observable.error(new RequestError5(3003, "notNetwork"));

        } else if (throwable instanceof SocketException) {

            return Observable.error(throwable);

        } else {

            return Observable.error(throwable);
        }

    }
}