package com.register

import android.app.Application
import com.base.router.BaseRouterApplication
import com.common.log.KLog

class RegisterApplication : BaseRouterApplication() {

    override fun onCreate() {
        super.onCreate()
        initApp(this)
    }

    override fun onInitHighPriority(application: Application): Boolean {
        initApp(application)
        return super.onInitHighPriority(application)
    }

    private fun initApp(application: Application) {
        KLog.e("=====RegisterApplication初始化========")
    }
}