package com.network.retrofit_coroutine_two.response

import java.io.Serializable

class BaseResponse<T> : Serializable {
    private val serialVersionUID: Long = 5760463722247936070L
    var cache = false
    var status: Int = -1
    var code: Int = -1
    var data: T? = null
    var errmsg: String? = null
}