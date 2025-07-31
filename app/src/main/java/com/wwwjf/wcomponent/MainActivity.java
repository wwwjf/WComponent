package com.wwwjf.wcomponent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.base.router.BaseRouterActivity;
import com.base.router.constant.ARouterPath;
import com.common.log.KLog;
import com.setting.export_setting.constant.ARouterPathSetting;
import com.wwwjf.wcomponent.databinding.ActivityMainBinding;

import kotlin.coroutines.jvm.internal.RunSuspendKt;

@Route(path = ARouterPath.MAIN_ACTIVITY)
public class MainActivity extends BaseRouterActivity {
    private ActivityMainBinding binding;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //viewbinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonSetting.setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterPathSetting.SETTING_ACTIVITY).navigation();
        });
        binding.buttonLogin.setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterPath.LOGIN_ACTIVITY).navigation();
        });

        binding.buttonMine.setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterPath.MINE_ACTIVITY).navigation();
        });

        binding.buttonSplash.setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterPath.SPLASH_ACTIVITY).navigation();
        });
        binding.buttonRegister.setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterPath.REGISTER_ACTIVITY).navigation();
        });
        binding.buttonModuleA.setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterPath.MODULE_A_ACTIVITY).navigation();
        });
    }



}