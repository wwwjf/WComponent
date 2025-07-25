package com.base.mvvm_two.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.network.retrofit_coroutine_two.response.BaseResponse

open class BaseViewModel: ViewModel() {

    var dataMap = mutableMapOf<String, MutableLiveData<BaseResponse<Any>>>()

}