package com.network.retrofit_coroutine

interface ResponseCallback<T> {
    fun onSuccess(value:T)

    fun onError(throwable: Throwable)

}