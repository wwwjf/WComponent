package com.network.retrofit_rxjava;

import com.network.retrofit_rxjava.constants.NetworkConstant;
import com.network.retrofit_rxjava.interceptor.CacheInterceptor;
import com.network.retrofit_rxjava.interceptor.HeaderInterceptor;
import com.network.retrofit_rxjava.interceptor.LoadParamsInterceptor;
import com.network.retrofit_rxjava.interceptor.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient mRetrofitClient;
    private Retrofit mRetrofit;

    private RetrofitClient(){
        //设置Http缓存
//        Cache cache = new Cache(new File(NetworkApp.getInstance().getCacheDir(), "HttpCache"),
//                1024 * 1024 * 10);
        LoggingInterceptor log = new LoggingInterceptor();
//        HttpLoggingInterceptor httpLog = new HttpLoggingInterceptor();
//        httpLog.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .cache(cache)
                .addInterceptor(log)
//                .addNetworkInterceptor(httpLog)
                .addInterceptor(new HeaderInterceptor())
                .addNetworkInterceptor(new LoadParamsInterceptor())
                .addNetworkInterceptor(new CacheInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();


        mRetrofit = new Retrofit.Builder()
                .baseUrl(NetworkConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static RetrofitClient getInstance(){
        if (mRetrofitClient == null){
            mRetrofitClient = new RetrofitClient();
        }
        return mRetrofitClient;
    }

    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }
}