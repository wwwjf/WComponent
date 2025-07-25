package com.module_a

import androidx.lifecycle.MutableLiveData
import com.base.mvvm_two.API
import com.base.mvvm_two.api.data.VersionMsgBean
import com.base.mvvm_two.base.BaseViewModel
import com.base.mvvm_two.go


class ModuleAViewModel : BaseViewModel() {

    var versionInfo = MutableLiveData<VersionMsgBean>()
    fun checkVersion() =go {
        API.checkVersion()
    }
}