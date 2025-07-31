package com.setting;

import android.app.Application;

import com.base.router.BaseRouterApplication;
import com.common.log.KLog;
import com.common.utils.NetworkUtil;

public class SettingApplication extends BaseRouterApplication {

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
        KLog.e("=====SettingApplication初始化========");
        NetworkUtil.init(application);
    }
}
