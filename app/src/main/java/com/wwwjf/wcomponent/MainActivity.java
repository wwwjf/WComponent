package com.wwwjf.wcomponent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.base.router.constant.ARouterPath;
import com.setting.export_setting.constant.ARouterPathSetting;

@Route(path = ARouterPath.MAIN_ACTIVITY)
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ARouter.getInstance().inject(this);

        findViewById(R.id.buttonSetting).setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterPathSetting.SETTING_ACTIVITY).navigation();
        });
        findViewById(R.id.buttonLogin).setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterPath.LOGIN_ACTIVITY).navigation();
        });

        findViewById(R.id.buttonMine).setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterPath.MINE_ACTIVITY).navigation();
        });

        findViewById(R.id.buttonSplash).setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterPath.SPLASH_ACTIVITY).navigation();
        });
    }



}