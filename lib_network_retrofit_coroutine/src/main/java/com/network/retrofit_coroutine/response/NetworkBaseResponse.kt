package com.network.retrofit_coroutine.response

data class NetworkBaseResponse<T>(
    val status: Int,
    val code: Int,
    val data: T?,
)