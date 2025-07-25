package com.base.mvvm_two

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import com.base.mvvm_two.base.BaseViewModel
import com.base.mvvm_two.utils.ApiDataCacheUtil
import com.common.eventbus.event.MessageEvent
import com.google.gson.Gson
import com.google.gson.stream.MalformedJsonException
import com.network.retrofit_coroutine_two.NetworkManager
import com.base.mvvm_two.api.ApiAnnotation
import com.network.retrofit_coroutine_two.annotation.RetrofitGoValue
import com.base.mvvm_two.api.INetworkApi
import com.common.log.KLog
import com.common.log.KLog.json
import com.network.retrofit_coroutine_two.constants.ApiConstant
import com.network.retrofit_coroutine_two.response.BaseResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.json.JSONException
import retrofit2.Call
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.jvm.javaClass


var Any.API: INetworkApi
    set(value) {}
    get() = initAPI(ApiConstant.BASE_URL, INetworkApi::class.java)

fun <T> Any.initAPI(url: String, clazz: Class<T>): T =
    NetworkManager.initRetrofit(url).create(clazz)

fun <T> BaseViewModel.go(block: () -> Call<BaseResponse<T>>): MutableLiveData<BaseResponse<T>> {

    var call = block()
    var path = call.request().url.toString()

    /**查找MutableLiveData，如果不存在，创建一个，并放在map里-----------------------------------------*/
    var data = dataMap[path.split("?")[0]]
    if (data == null) {
        data = MutableLiveData()
        dataMap[path.split("?")[0]] = data
    }

    viewModelScope.launch(Dispatchers.IO) {

        var retrofitGoValue: RetrofitGoValue? = null

        var responseBean = try {
            /**获取注解配置，查看是否启用缓存--------------------------------------------------------*/
            retrofitGoValue = getRetrofitGoValue(path)
            if (retrofitGoValue.cache) {
                initCache(retrofitGoValue, path, data, viewModelScope)
            }
            /**获取网络数据-------------------------------------------------------------------------*/
//            delay(2000)
            var res = call.execute()
            if (res != null && res.isSuccessful) {
                res.body()
            } else {
                var bean = BaseResponse<T>()
                if (res.code() == 404) bean.status = 404
                bean
            }
        } catch (e: Exception) {
            apiException<T>(e)
        }

        /**设置MutableLiveData.value----------------------------------------------------------------*/
        viewModelScope.launch(Dispatchers.Main) {
            data.value = responseBean as BaseResponse<Any>
        }

        /**把数据更新到缓存--------------------------------------------------------------------------*/
        if (responseBean?.code == ApiConstant.SUCCESS) {
            ApiDataCacheUtil.putCache(path, responseBean);
        }

        /**关闭loading-----------------------------------------------------------------------------*/
        delay(100)
//        RxBus.getDefault().post(RxBusEvent(31415927, path))
        EventBus.getDefault().post(MessageEvent(EventType.EVENT_CLOSE_LOADING))
    }
    return data!! as MutableLiveData<BaseResponse<T>>
}


/**
 * 通过路径path，查找与路径value相等的方法，获取MyRetrofitGo注解loading和cache
 */
fun getRetrofitGoValue(path: String): RetrofitGoValue {
    ApiAnnotation.value.forEach {
        if (path.split("?")[0].endsWith(it.key)) {
            return it.value
        }
    }
    return RetrofitGoValue(loading = true, cache = true, hasCacheLoading = false, tag = "")
}

/**对象
 * MutableLiveData<BaseEntity<T>简写方案
 */
fun <T> ViewModel.initData() = lazy { MutableLiveData<BaseResponse<T>>() }

/**数组
 * MutableLiveData<BaseEntity<ArrayList<T>>>简写方案
 */
fun <T> ViewModel.initDatas() = lazy { MutableLiveData<BaseResponse<ArrayList<T>>>() }


/**
 * 网络数据请求成功
 */
fun <T> BaseResponse<T>.success(func: () -> Unit) {
    if (this.code == ApiConstant.SUCCESS && !this.cache) func()
}

/**
 * 缓存数据请求成功
 */
fun <T> BaseResponse<T>.cache(func: () -> Unit) {
    if (this.code == ApiConstant.SUCCESS && this.cache) func()
}

/**
 * 网络数据请求失败
 */
fun <T> BaseResponse<T>.error(func: () -> Unit) {
    if (this.code != ApiConstant.SUCCESS && !this.cache) func()
}

/**
 * 观察者监听数据变化
 */
fun <T> MutableLiveData<T>.obs(owner: LifecycleOwner, func: (t: T) -> Unit) = this.apply {
    if (!this.hasObservers())
        this.observe(owner, Observer<T> { func(it) })
}


/**
 * 从缓存里获取数据,没缓存时，显示loadingDialog，并执行response方法
 */
fun <T> initCache(
    retrofitGoValue: RetrofitGoValue,
    path: String,
    data: MutableLiveData<BaseResponse<T>>,
    viewModelScope: CoroutineScope
) {
    var hasCache = false
    if (path.isNotEmpty()) {
        var cacheResponse = ApiDataCacheUtil.getCache(path, BaseResponse<T>()::class.java)
        if (cacheResponse != null) {
            cacheResponse.cache = true
            viewModelScope.launch(Dispatchers.Main) {
                val jsonTree = Gson().toJsonTree(cacheResponse.data)
                val isJsonNull = jsonTree.isJsonNull
                val isJsonObject = jsonTree.isJsonObject
                val isJsonArray =jsonTree.isJsonArray
                if (isJsonObject) {
                    //转换为实体类
                } else if (isJsonArray) {
                    //转换为实体类
                }
                KLog.e("==cache====isJsonNull=$isJsonNull,isJsonObject=$isJsonObject,isJsonArray=$isJsonArray")
                data.value = cacheResponse
            }
            hasCache = true
        }
    }

    if (hasCache) {
        if (retrofitGoValue.hasCacheLoading) {
            EventBus.getDefault().post(MessageEvent(EventType.EVENT_SHOW_LOADING))//显示dialog
        }
    } else if (retrofitGoValue.loading) {
        EventBus.getDefault().post(MessageEvent(EventType.EVENT_SHOW_LOADING))//显示dialog
    }

}

/**
 * 网络异常处理
 */
fun <T> apiException(e: Exception): BaseResponse<T> {
    e.printStackTrace()
    var bean = BaseResponse<T>()
    when (e) {
        is SocketTimeoutException -> bean.errmsg = "网络超时"
        is HttpException -> {
            when {
                e.code() == 403 -> bean.errmsg = "访问被拒绝"
                e.code() == 404 -> bean.errmsg = "找不到路径"
                e.code().toString().startsWith("4") -> bean.errmsg = "客户端异常"
                e.code().toString().startsWith("5") -> bean.errmsg = "服务器异常"
            }
        }

        is UnknownHostException -> bean.errmsg = "找不到服务器，请检查网络"
        is JSONException -> bean.errmsg = "数据解析异常，非法JSON"
        is MalformedJsonException -> bean.errmsg = "数据解析异常，非法JSON"
        is Exception -> bean.errmsg = "程序异常" + e.javaClass.name
    }
    return bean
}


/**
 *简写ViewModel创建
 */

fun <T : ViewModel> Any.initVM(fa: FragmentActivity, modelClass: Class<T>) =
    ViewModelProviders.of(fa).get(modelClass)

/**
 *简写ViewModel创建
 */

fun <T : ViewModel> FragmentActivity.initVM(modelClass: Class<T>) =
    ViewModelProviders.of(this).get(modelClass)

/**
 *简写ViewModel创建
 */
fun <T : ViewModel> Fragment.initVM(modelClass: Class<T>) =
    ViewModelProviders.of(this).get(modelClass)

fun String.log() {
    Log.i("MyLog", this)
}


fun Any.toJson(): String = Gson().toJson(this)