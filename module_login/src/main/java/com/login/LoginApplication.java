package com.login;

import android.app.Application;

import com.base.router.BaseRouterApplication;
import com.common.log.KLog;

public class LoginApplication extends BaseRouterApplication {

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

    private void initApp(Application application) {
        KLog.e("=====LoginApplication初始化========");
    }
}
