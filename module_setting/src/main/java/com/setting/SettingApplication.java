package com.setting;

import com.base.router.BaseRouterApplication;
import com.common.utils.NetworkUtil;

public class SettingApplication extends BaseRouterApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkUtil.init(this);
    }
}
