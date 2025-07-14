package com.network.retrofit_rxjava_two.helper;


import com.common.log.KLog;
import com.common.utils.StringUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 拦截器工具类
 */

public class InterceptorHelper {
    public static String TAG = InterceptorHelper.class.getSimpleName();

    /**
     * 日志拦截器
     */
    public static Interceptor getLogInterceptor() {
//        if (BuildConfig.DEBUG) {
        boolean isDebug = true;
        if (isDebug) {
            return new HttpLogger().setLevel(HttpLogger.Level.BODY);
//            return new HttpLoggingInterceptor(message -> KLog.e(TAG, "---------: " + message)).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
        } else {
            return new HttpLogger().setLevel(HttpLogger.Level.NONE);
        }
    }

    /**
     * 缓存拦截器
     */
    public static Interceptor getCacheInterceptor() {
        return chain -> {
            Request request = chain.request();
            int maxStale = 4 * 7 * 24 * 60; // 离线时缓存保存4周,单位:秒
            CacheControl tempCacheControl = new CacheControl.Builder()
                    .onlyIfCached()
                    .maxStale(maxStale, TimeUnit.SECONDS)
                    .build();
            request = request.newBuilder()
                    .cacheControl(tempCacheControl)
                    .build();
            return chain.proceed(request);
        };
    }

    /**
     * 获取cookie
     */
    public static Interceptor getCookieInterceptor() {

        return chain -> {
            Request request = chain.request();
            Response originalResponse = chain.proceed(request);

            if (!originalResponse.headers("Set-cookie").isEmpty()) {

                KLog.e("=============返回的 Set-cookie 不为空，保存cookie start ======================");
                List<String> headers = originalResponse.headers("Set-cookie");
                for (String header : headers) {
                    if (header.contains("randomkey")) {
//                        UserInfoManager.setRandomKey(header);
                    }
                }
                HashSet<String> set = new HashSet<>(headers);
//                UserInfoManager.setCookie(set);
                KLog.e("=============返回的 Set-cookie 不为空，保存cookie end ======================");

            }
            return originalResponse;
        };
    }

    /**
     * 设置cookie
     */
    public static Interceptor setCookieInterceptor() {
        return chain -> {
            final Request.Builder builder = chain.request().newBuilder();
//            Set<String> cookie = UserInfoManager.getCookie();
            Set<String> cookie = new HashSet<>();
            if (cookie != null) {
                KLog.e("=============本地 cookie 不为空，添加cookie start ======================");
                Iterator<String> headerIterator = cookie.iterator();
                while (headerIterator.hasNext()) {
                    builder.addHeader("Cookie", headerIterator.next());
                }
                KLog.e("=============本地 cookie 不为空，添加cookie end ======================");
            }
//            String sessionId = UserInfoManager.getSessionId();
            String sessionId = "";
            if (sessionId != null && !"".equals(sessionId)) {
                builder.addHeader("Cookie", "SESSION=" + sessionId);
            }
//            Set<String> randomKeySet = UserInfoManager.getRandomKey();
            Set<String> randomKeySet = new HashSet<>();
            if (randomKeySet != null) {
                for (String randomKey : randomKeySet) {
                    if (StringUtil.isTrimEmpty(randomKey)) {
                        continue;
                    }
                    builder.addHeader("Cookie", randomKey);
                }
            }
            /*if (!StringUtil.isTrimEmpty(BuildConfig.VERSION_NAME)){
                builder.addHeader("APP_VERSION",StringUtil.valueOf(BuildConfig.VERSION_NAME));
            }*/
//            String accessKey = UserInfoManager.getAccessKey();
            String accessKey = "";
            if (accessKey != null && !"".equals(accessKey)) {
                builder.addHeader("EPAY-ACCESS-KEY", accessKey);
            }
//            String slideAccessKey = UserInfoManager.getSlideAccessKey();
//            if (!StringUtil.isTrimEmpty(slideAccessKey)){
//                builder.addHeader(EPayConstant.UserInfo.SLIDE_ACCESS_KEY,slideAccessKey);
//            }
            builder.addHeader("Connection", "keep-alive");
//                    .addHeader("User-Agent", DeviceUtil.getClientInfo())
//                    .addHeader("Content-Language", LanguageManager.getLanguageCode())
//                    .addHeader("lan", LanguageManager.getLanguageCode())
//                    .addHeader("source", StringUtil.valueOf(EPayConstant.SourceClient.ANDROID));
            return chain.proceed(builder.build());
        };
    }


    /**
     * 重试拦截器
     */
    public static Interceptor getRetryInterceptor() {
        return chain -> {
            int maxRetry = 10;//最大重试次数
            int retryNum = 5;//假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）

            Request request = chain.request();
            Response response = chain.proceed(request);
            while (!response.isSuccessful() && retryNum < maxRetry) {
                retryNum++;
                response = chain.proceed(request);
            }
            return response;
        };
    }

    /**
     * 请求头拦截器
     */
    public static Interceptor getHeaderInterceptor() {
        return chain -> {
            //在这里你可以做一些想做的事,比如token失效时,重新获取token
            //或者添加header等等

            Request originalRequest = chain.request();

            if (null == originalRequest.body()) {
                return chain.proceed(originalRequest);
            }

            Request compressedRequest = originalRequest.newBuilder()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
//                    .addHeader("User-Agent", DeviceUtil.getClientInfo())
                    .addHeader("Connection", "keep-alive")
//                    .addHeader("Content-Language", LanguageManager.getLanguageCode())
//                    .addHeader("lan", LanguageManager.getLanguageCode())
//                    .addHeader("source", StringUtil.valueOf(EPayConstant.SourceClient.ANDROID))
                    .build();
            return chain.proceed(compressedRequest);
        };

    }

    public static Map<String, String> getHeaderMap() {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json;charset=UTF-8");
//        headerMap.put("User-Agent", DeviceUtil.getClientInfo());
        headerMap.put("Connection", "keep-alive");
//        headerMap.put("Content-Language", LanguageManager.getLanguageCode());
//        headerMap.put("lan", LanguageManager.getLanguageCode());
//        headerMap.put("source", StringUtil.valueOf(EPayConstant.SourceClient.ANDROID));
        return headerMap;
    }

    public static Interceptor setDownloadProgressInterceptor() {
        return chain -> {
            Response originalResponse = chain.proceed(chain.request());

            return originalResponse.newBuilder()
                    .build();
        };
    }

    public static Interceptor setUploadProgressInterceptor() {
        return chain -> {
            final Request.Builder builder = chain.request().newBuilder();
            Request request = builder.build();
            Response response = chain.proceed(request);
            return response;
        };
    }
}