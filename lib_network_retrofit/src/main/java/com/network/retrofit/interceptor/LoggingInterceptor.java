package com.network.retrofit.interceptor;

import android.util.Log;

import com.common.log.KLog;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 日志打印
 */
public class LoggingInterceptor implements Interceptor {
    public static final String TAG = LoggingInterceptor.class.getSimpleName();
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Log.v(TAG, "request:" + request.toString());
        long t1 = System.nanoTime();
        Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        Log.v(TAG, String.format(Locale.getDefault(), "Received response %s for %s in %.1fms%n%s",
                response.code(),response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        MediaType mediaType = response.body() != null ? response.body().contentType() : null;
        String content = response.body() != null ? response.body().string() : null;
//        Log.i(TAG, "response body:" + content);
//        Logger.json(content);
        KLog.json(content);
        return response.newBuilder().body(ResponseBody.create(mediaType, content != null ? content : "")).build();

    }
}