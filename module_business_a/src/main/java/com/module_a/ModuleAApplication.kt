package com.module_a

import com.base.mvvm_two.utils.ContextUtil
import com.base.router.BaseRouterApplication


class ModuleAApplication: BaseRouterApplication() {

    override fun onCreate() {
        super.onCreate()
        ContextUtil.context = this
    }
}