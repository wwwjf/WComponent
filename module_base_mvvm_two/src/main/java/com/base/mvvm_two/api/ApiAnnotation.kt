package com.base.mvvm_two.api

import com.network.retrofit_coroutine_two.annotation.RetrofitGo
import com.network.retrofit_coroutine_two.annotation.RetrofitGoValue
import retrofit2.http.GET
import retrofit2.http.POST

object ApiAnnotation {

    var value = HashMap<String, RetrofitGoValue>()

    init {
        initRetrofitGoValue(INetworkApi::class.java)
    }


    /**
     * 获取RetrofitGo注解loading和cache
     */
    fun <T> initRetrofitGoValue(cla: Class<T>){
        var methods = cla.methods
        loop@ for (method in methods) {

            var postAnnotation = method.getAnnotation(POST::class.java)
            var getAnnotation = method.getAnnotation(GET::class.java)
            var path:String?=null
            when {
                postAnnotation != null -> path= postAnnotation.value
                getAnnotation != null -> path=getAnnotation.value
                else -> break@loop
            }

            var loading = method.getAnnotation(RetrofitGo::class.java).loading
            var cache = method.getAnnotation(RetrofitGo::class.java).cache
            var hasCacheLoading = method.getAnnotation(RetrofitGo::class.java).hasCacheLoading
            var tag = method.getAnnotation(RetrofitGo::class.java).tag

            value[path] = RetrofitGoValue(loading, cache, hasCacheLoading, tag)
        }
    }
}