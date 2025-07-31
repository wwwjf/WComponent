package com.module_a

import android.app.Application
import com.base.mvvm_two.utils.ContextUtil
import com.base.router.BaseRouterApplication
import com.common.log.KLog


class ModuleAApplication: BaseRouterApplication() {

    override fun onCreate() {
        super.onCreate()
        initApp(this)
    }

    override fun onInitHighPriority(application: Application): Boolean {
        initApp(application)
        return super.onInitHighPriority(application)
    }

    private fun initApp(application: Application) {
        KLog.e("=====ModuleAApplication初始化========")
        ContextUtil.context = application
    }
}