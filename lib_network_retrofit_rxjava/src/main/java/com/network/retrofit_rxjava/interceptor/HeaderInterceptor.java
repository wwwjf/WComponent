package com.network.retrofit_rxjava.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request request;
        Request.Builder requestBuilder = originalRequest.newBuilder();
        if (originalRequest.url().host().contains("api.mpen.com.cn")) {
            //根据url添加公共请求头
            requestBuilder.addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .addHeader("Accept-Encoding", "gzip")
                    .addHeader("Accept-Language", "zh-CN")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Authorization", "Bearer example123");
        }
        request = requestBuilder.build();
        return chain.proceed(request);
    }
}
