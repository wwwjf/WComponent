package com.base.router;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.base.router.application.IApplicationInit;

/**
 * 单独运行调试模式的启动类
 */
public class BaseRouterApplication extends Application implements IApplicationInit {

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

    @Override
    public boolean onInitHighPriority(Application application) {
        return true;
    }

    @Override
    public boolean onInitLowPriority(Application application) {
        return true;
    }
}
