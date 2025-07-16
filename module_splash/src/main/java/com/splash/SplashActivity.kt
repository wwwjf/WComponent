package com.splash

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.base.router.constant.ARouterPath
import com.business.mvvm.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.splash.common.SharedViewModel

@Route(path = ARouterPath.SPLASH_ACTIVITY)
class SplashActivity : BaseActivity() {
    private lateinit var mSplashViewModel: SplashViewModel
    private lateinit var mSharedViewModel: SharedViewModel

    override fun initViewModel() {
        mSplashViewModel = getActivityScopeViewModel(SplashViewModel::class.java)
        mSharedViewModel = getApplicationScopeViewModel(SharedViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_splash, BR.splashVM, mSplashViewModel)
            .addBindingParam(BR.click, ClickProxy())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    class ClickProxy {
        fun jumpMine() {
            ARouter.getInstance().build(ARouterPath.MINE_ACTIVITY).navigation()
        }
    }
}