package com.network.retrofit_coroutine

import android.util.Log
import com.common.log.KLog
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.util.Locale

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        KLog.d("request:$request")
        val t1 = System.nanoTime()
        val response = chain.proceed(chain.request())
        val t2 = System.nanoTime()
        KLog.d(
            String.format(
                Locale.getDefault(), "Received response %s for %s in %.1fms%n%s",
                response.code, response.request.url, (t2 - t1) / 1e6, response.headers
            )
        )
        val mediaType = if (response.body != null) response.body!!.contentType() else null
        val content = if (response.body != null) response.body!!.string() else null
        KLog.json(content)
        return response.newBuilder().body(ResponseBody.create(mediaType, content ?: "{}")).build()

    }
}