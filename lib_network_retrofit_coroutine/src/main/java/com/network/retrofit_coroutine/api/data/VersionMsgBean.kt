package com.network.retrofit_coroutine.api.data

import com.google.gson.annotations.SerializedName
import java.io.Serial
import java.io.Serializable

data class VersionMsgBean(
    val vCode: Int,
    val vName: String,
    @SerializedName("cn") val cnMsg: String,
    @SerializedName("en") val enMsg: String,
    val isForce: Int,
    val isShowContent: Int,
    val url: String,
) : Serializable {
    companion object {
        @Serial
        private const val serialVersionUID: Long = -7845653905714163453L
    }
}
