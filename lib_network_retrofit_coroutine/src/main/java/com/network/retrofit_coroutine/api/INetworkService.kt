package com.network.retrofit_coroutine.api

import com.network.retrofit_coroutine.api.data.VersionMsgBean
import com.network.retrofit_coroutine.response.NetworkBaseResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


interface NetworkServiceApi {

    @GET("apps/android/android.v")
    fun checkVersion_deferred(): Deferred<NetworkBaseResponse<VersionMsgBean?>>

    @GET("apps/android/android.v")
    suspend fun checkVersion_suspend(): NetworkBaseResponse<VersionMsgBean?>
}