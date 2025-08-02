package com.splash

import android.app.Application
import com.business.BaseMvvmApplication
import com.common.datastore.DatastoreManager
import com.common.log.KLog
import com.common.utils.NetworkUtil

class SplashApplication: BaseMvvmApplication() {

    override fun onCreate() {
        super.onCreate()
        initApp(this)
    }

    private fun initApp(application: Application) {
        KLog.e("=====SplashApplication初始化========")
        NetworkUtil.init(application)
    }

    override fun onInitHighPriority(application: Application): Boolean {
        super.onInitHighPriority(application)
        initApp(application)
        return true
    }

    override fun onInitLowPriority(application: Application): Boolean {
        initApp(application)
        return true
    }

}