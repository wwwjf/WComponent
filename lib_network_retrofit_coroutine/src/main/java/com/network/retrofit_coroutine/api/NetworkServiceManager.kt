package com.network.retrofit_coroutine.api

import com.network.retrofit_coroutine.RetrofitClient
import com.network.retrofit_coroutine.api.data.VersionMsgBean
import com.network.retrofit_coroutine.response.NetworkBaseResponse
import kotlinx.coroutines.Deferred

object NetworkServiceManager {

    private val serviceApi: NetworkServiceApi =
        RetrofitClient.getInstance().create(NetworkServiceApi::class.java)

    fun checkVersionDeferred(): Deferred<NetworkBaseResponse<VersionMsgBean?>> {
        return serviceApi.checkVersion_deferred()
    }

    suspend fun checkVersionSuspend(): VersionMsgBean {
        val response = serviceApi.checkVersion_suspend();
        if (response.code == 0) {
            throw Exception("code is ${response.code}")
        }
        return response.data ?: throw Exception("data is null")
    }
}