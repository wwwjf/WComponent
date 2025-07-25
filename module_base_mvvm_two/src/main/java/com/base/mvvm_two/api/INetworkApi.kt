package com.base.mvvm_two.api

import com.network.retrofit_coroutine_two.response.BaseResponse
import com.network.retrofit_coroutine_two.annotation.RetrofitGo
import com.base.mvvm_two.api.data.VersionMsgBean
import retrofit2.Call
import retrofit2.http.GET

interface INetworkApi {


    /**
     * 检查版本
     */
    @RetrofitGo(loading = true, cache = true, hasCacheLoading = false)
    @GET("apps/android/android.v")
    fun checkVersion(): Call<BaseResponse<VersionMsgBean>>

}