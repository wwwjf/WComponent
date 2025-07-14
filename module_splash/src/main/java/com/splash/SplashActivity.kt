package com.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.base.router.constant.ARouterPath
import com.splash.databinding.ActivitySplashBinding

@Route(path = ARouterPath.SPLASH_ACTIVITY)
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ARouter.getInstance().inject(this)
        binding.buttonMine.setOnClickListener {
            ARouter.getInstance().build(ARouterPath.MINE_ACTIVITY).navigation()
        }

    }
}