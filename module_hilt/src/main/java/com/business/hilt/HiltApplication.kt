package com.business.hilt

import android.app.Application
import com.base.router.BaseRouterApplication
import com.common.log.KLog
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class HiltApplication : BaseRouterApplication() {

    override fun onCreate() {
        super.onCreate()
        initApp(this)
    }

    override fun onInitHighPriority(application: Application): Boolean {
        initApp(application)
        return super.onInitHighPriority(application)
    }

    fun initApp(application: Application) {
        KLog.e("=====HiltApplication初始化========")
    }
}