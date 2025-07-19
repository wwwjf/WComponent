package com.network.retrofit_coroutine

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.network.retrofit_coroutine.constants.ApiConstant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 创建单例的方法：
 * 1、object 类
 * 2、class类中定义伴生对象
 * 3、枚举
 * 4、懒加载 by lazy 实例化对象(内部实现 synchronize加锁，volatile关键字)
 */
class RetrofitClient private constructor() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(ApiConstant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        //添加对 Deferred 的支持
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(
            OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor())
                .build()
        )
        .build()

    companion object {
        @Volatile
        private var retrofitClient: RetrofitClient? = null
        fun getInstance(): RetrofitClient {
            return retrofitClient ?: synchronized(this) {
                /*retrofitClient ?: RetrofitClient().run {
                    retrofitClient = this
                    return retrofitClient!!
                }*/
                //run with apply 函数块内使用this,also let 函数块内使用it
                //apply also 返回值是该对象本身 run with let 返回值是最后一行的结果
                /*retrofitClient?: with(RetrofitClient){
                    retrofitClient = this//with不是扩展函数，this指代不明，无法使用该关键字
                    return retrofitClient!!
                }*/
//                retrofitClient?:RetrofitClient().apply { retrofitClient = this }
                retrofitClient ?: RetrofitClient().also { retrofitClient = it }
                /*retrofitClient?:RetrofitClient().let {
                    retrofitClient = it
                    retrofitClient!!
                }*/
            }
        }
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}