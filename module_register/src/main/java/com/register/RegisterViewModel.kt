package com.register

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.common.log.KLog
import com.network.retrofit_coroutine.api.NetworkServiceManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

class RegisterViewModel : ViewModel() {

    val versionMsg: MutableLiveData<String> = MutableLiveData("")
    val msg: ObservableField<String> = ObservableField<String>("")

    fun checkVersion() {
        //1、协程Deferred请求数据
        viewModelScope.launch(Dispatchers.IO) {
            val networkBaseResponse = NetworkServiceManager.checkVersionDeferred().await()
            viewModelScope.launch(Dispatchers.Main) {
                networkBaseResponse.let {
                    val versionMsgBean = it.data
                    val info = "deferred:${versionMsgBean?.cnMsg}\n"
                    versionMsg.value += info
                    KLog.e("msg====${versionMsg.value}")
                }
            }
        }

        //2、协程suspend请求数据
        viewModelScope.launch(Dispatchers.IO) {
            val versionMsgBean = NetworkServiceManager.checkVersionSuspend()
            viewModelScope.launch(Dispatchers.Main) {
                val info = "suspend:${versionMsgBean.cnMsg}\n"
                versionMsg.value += info
                KLog.e("msg====${versionMsg.value}")
            }
        }
        //协程拦截器
        viewModelScope.launch { coroutineInterceptor(this) }
        suspend { coroutineInterceptor(viewModelScope) }

        //协程async请求数据
        viewModelScope.launch {
            val async = viewModelScope.async { }
            async.await()
        }

        viewModelScope.launch {
            val producer = viewModelScope.produce {
                KLog.e(1)
                delay(1000)
                KLog.e(2)
                send("Hello")
            }
        }

        runCatching {
            viewModelScope.async {  }
        }


        flow {
         emit(1)
        }.flowOn(Dispatchers.IO).catch { t->
            t.printStackTrace()
        }

    }

    suspend fun coroutineInterceptor(viewModelScope: CoroutineScope) {

        viewModelScope.launch(MyContinuationInterceptor()) {
            KLog.e(1)
            val job = async {
                KLog.e(2)
                delay(1000)
                KLog.e(3)
                "Hello"
            }
            log(4)
            val result = job.await()
            log("5. $result")
        }.join()
        log(6)
    }
}


class MyContinuation<T>(val continuation: Continuation<T>) : Continuation<T> {
    override val context: CoroutineContext = continuation.context
    override fun resumeWith(result: Result<T>) {
        KLog.e("result:$result")
        continuation.resumeWith(result)
    }

}

class MyContinuationInterceptor : ContinuationInterceptor {
    override fun <T> interceptContinuation(continuation: Continuation<T>): MyContinuation<T> =
        MyContinuation(continuation)

    override val key = ContinuationInterceptor

}