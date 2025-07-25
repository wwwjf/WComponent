package com.register

import android.R.attr.value
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.common.log.KLog
import com.network.retrofit_coroutine.api.NetworkServiceManager
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.supervisorScope
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

class RegisterViewModel : ViewModel() {

    val versionMsg: MutableLiveData<String> = MutableLiveData("")
    val msg: ObservableField<String> = ObservableField<String>("")

    fun checkVersion() {
        //1、协程Deferred请求数据
        viewModelScope.launch {
            KLog.e("===thread=${Thread.currentThread().name}")
            val networkBaseResponse = NetworkServiceManager.checkVersionDeferred().await()
            networkBaseResponse.let {
                val versionMsgBean = it.data
                val info = "deferred:${versionMsgBean?.cnMsg}\n"
                versionMsg.value += info
                KLog.e("msg====${versionMsg.value}")
            }
        }

        //2、协程suspend请求数据
        viewModelScope.launch {
            KLog.e("===thread=${Thread.currentThread().name}")
            val versionMsgBean = NetworkServiceManager.checkVersionSuspend()
            val info = "suspend:${versionMsgBean.cnMsg}\n"
            delay(1000)
            versionMsg.value += info
            KLog.e("msg====${versionMsg.value}")
        }
        //协程拦截器
        viewModelScope.launch { coroutineInterceptor(this) }
        suspend { coroutineInterceptor(viewModelScope) }

        //协程async请求数据
        viewModelScope.launch {
            val async = viewModelScope.async { }
            async.await()
        }

        //协程生产者
        viewModelScope.launch {
            val producer: ReceiveChannel<String> = viewModelScope.produce {
                KLog.e(1)
                delay(1000)
                KLog.e(2)
                send("Hello")
            }
            KLog.e("==consume===it=${producer.receive()}")
        }
        //协程actor
        viewModelScope.launch {
            val actor: SendChannel<String> = viewModelScope.actor<String> {
                ""
            }
        }


        viewModelScope.launch(CoroutineExceptionHandler{ _, throwable ->{
            KLog.e("throwable===$throwable")
        }}) {
            coroutineScope { throw NullPointerException("coroutineScope null pointer") }
            supervisorScope {  }

        }
        viewModelScope.runCatching {
            var i = 0
            while (i < 10) {
                if (i == 5) {
                    throw NullPointerException("error")
                } else {
                    KLog.e("===i=$i")
                }
                i++
            }
        }.onSuccess {
            KLog.e("onSuccess======")
        }.onFailure {
            KLog.e("onFailure======")
        }


        flow {
            emit(1)
        }.flowOn(Dispatchers.IO).catch { t ->
            t.printStackTrace()
        }

        viewModelScope.launch {
            handleChannel()
        }

        // select多路复用
        viewModelScope.launch {
            val deferredLocal = async {
                return@async "本地缓存数据"
            }
            val deferredNet = async {
                delay(1000)
                KLog.e("select====缓存网络数据")
                return@async "网络数据"
            }
            val result = select<String> {
                deferredLocal.onAwait {
                    it
                }
                deferredNet.onAwait {
                    it
                }
            }

            KLog.e("select==result===$result")
            val result2 = {

            }

        }

    }

    suspend fun handleChannel() {
        val channel = Channel<Any>()
        val producer = viewModelScope.launch {
            channel.send(1)
            delay(1000)
            channel.send(2)
            delay(1000)
            channel.send(3)
            channel.close()
        }
        val consumer = viewModelScope.launch {
            for (element in channel) {
                KLog.e("value===$element")
            }
        }
        producer.join()
        consumer.join()
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
    override fun onCleared() {
        super.onCleared()
    }
}


class MyContinuation<T>(val continuation: Continuation<T>) : Continuation<T> {
    override val context: CoroutineContext = continuation.context
    override fun resumeWith(result: Result<T>) {
        KLog.e("result:$result")
        continuation.resumeWith(result)
    }

}

//自定义拦截器
class MyContinuationInterceptor : ContinuationInterceptor {
    override fun <T> interceptContinuation(continuation: Continuation<T>): MyContinuation<T> =
        MyContinuation(continuation)

    override val key = ContinuationInterceptor

}