package com.splash

import com.base.router.BaseRouterApplication
import com.common.utils.NetworkUtil

class SplashApplication: BaseRouterApplication() {

    override fun onCreate() {
        super.onCreate()
        NetworkUtil.init(this)
    }

}