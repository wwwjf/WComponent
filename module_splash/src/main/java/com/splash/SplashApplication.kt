package com.splash

import com.business.BaseMvvmApplication
import com.common.utils.NetworkUtil

class SplashApplication: BaseMvvmApplication() {

    override fun onCreate() {
        super.onCreate()
        NetworkUtil.init(this)
    }

}