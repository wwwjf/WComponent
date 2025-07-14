package com.network.retrofit_rxjava_two;



import com.network.retrofit_rxjava_two.constant.Api;
import com.network.retrofit_rxjava_two.helper.HttpConfig;
import com.network.retrofit_rxjava_two.helper.InterceptorHelper;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitClient {
    private static String TAG = RetrofitClient.class.getSimpleName();
    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    private static volatile RetrofitClient mRetrofitClient;
    private static ApiService5 mAPI;

    private RetrofitClient() {

        initOkHttpClient();
        initRetrofit();
        initWebSocket();
    }

    public static RetrofitClient getInstance() {
        if (mRetrofitClient == null) {
            synchronized (RetrofitClient.class) {
                if (mRetrofitClient == null) {
                    mRetrofitClient = new RetrofitClient();
                }
            }
        }
        return mRetrofitClient;
    }

    /**
     * 获取 Retrofit
     */
    private void initRetrofit() {


        mRetrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                //JSON转换器,使用Gson来转换
                .addConverterFactory(GsonConverterFactory.create())
                //RxJava适配器
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
        mAPI = mRetrofit.create(ApiService5.class);
    }

    private void initWebSocket() {

        //TODO websocket
/*        Config config = new Config.Builder()
                .setClient(mOkHttpClient)   //if you want to set your okhttpClient
                .setShowLog(BuildConfig.DEBUG, "EPayWebSocketLog") //show  log
                .setReconnectInterval(5, TimeUnit.SECONDS)  //set reconnect interval
                .setSSLSocketFactory(SSLSocketClient5.getInstance(),SSLSocketClient5.getInstance().getTrustCert()) // wss support
                .build();
        RxWebSocket.setConfig(config);*/
    }


    /**
     * 单例模式获取 OkHttpClient
     */
    private static void initOkHttpClient() {

        if (mOkHttpClient == null) {

            synchronized (RetrofitClient.class) {

                if (mOkHttpClient == null) {
                    // 指定缓存路径,缓存大小100Mb
//                    Cache cache = new Cache(new File(HttpConfig.DIR_CACHE_FILE, "HttpCache"),
//                            1024 * 1024 * 100);
                    CookieManager cookieManager = new CookieManager();
                    cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
                    SSLSocketClient5.getInstance();
                    mOkHttpClient = new OkHttpClient.Builder()
                            //设置连接超时时间
                            .connectTimeout(HttpConfig.HTTP_TIME_OUT_TIME, TimeUnit.SECONDS)
                            //设置读取超时时间
                            .readTimeout(HttpConfig.HTTP_TIME_OUT_TIME, TimeUnit.SECONDS)
                            //设置写入超时时间
                            .writeTimeout(HttpConfig.HTTP_TIME_OUT_TIME, TimeUnit.SECONDS)
//                            .cookieJar(new JavaNetCookieJar(cookieManager))
                            .pingInterval(5, TimeUnit.SECONDS)//webSocket 心跳检测
                            //默认重试一次
//                            .retryOnConnectionFailure(false)
                            //下载进度条拦截器
//                            .addInterceptor(InterceptorHelper.setDownloadProgressInterceptor())
                            //添加请求头拦截器
//                            .addInterceptor(InterceptorHelper.getHeaderInterceptor())
                            //添加cookie拦截器
                            .addInterceptor(InterceptorHelper.getCookieInterceptor())
                            .addInterceptor(InterceptorHelper.setCookieInterceptor())
                            //添加缓存拦截器
//                            .addInterceptor(InterceptorHelper.getCacheInterceptor())
                            //添加重试拦截器
//                            .addInterceptor(InterceptorHelper.getRetryInterceptor())
                            //添加日志拦截器
                            .addInterceptor(InterceptorHelper.getLogInterceptor())
                            // 信任Https,忽略Https证书验证
                            // https认证,如果要使用https且为自定义证书 可以去掉这两行注释，并自行配制证书。
//                            .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                            .sslSocketFactory(SSLSocketClient5.getInstance(), SSLSocketClient5.getInstance().getTrustCert())
                            .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                            //缓存
//                            .cache(cache)
                            .build();
                }
            }
        }
    }

    /**
     * 对外提供调用 API的接口
     *
     * @return
     */
    public ApiService5 getAPIService() {
        return mAPI;
    }
}