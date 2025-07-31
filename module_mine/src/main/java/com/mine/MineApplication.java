package com.mine;

import android.app.Application;

import com.base.router.BaseRouterApplication;
import com.common.log.KLog;

public class MineApplication extends BaseRouterApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initApp(this);
    }

    @Override
    public boolean onInitHighPriority(Application application) {
        initApp(application);
        return super.onInitHighPriority(application);
    }


    @Override
    public boolean onInitLowPriority(Application application) {
        return super.onInitLowPriority(application);
    }


    private void initApp(Application application) {
        KLog.e("=====MineApplication初始化========");
    }
}
