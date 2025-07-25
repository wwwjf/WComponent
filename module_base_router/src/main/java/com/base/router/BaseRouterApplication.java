package com.base.router;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.common.log.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 单独运行调试模式的启动类
 */
public class BaseRouterApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.isModuleDebug || BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
