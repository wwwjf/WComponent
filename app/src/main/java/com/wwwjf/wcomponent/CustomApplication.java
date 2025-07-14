package com.wwwjf.wcomponent;

import com.base.router.BaseRouterApplication;
import com.common.utils.NetworkUtil;

public class CustomApplication extends BaseRouterApplication {

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkUtil.init(this);
    }
}
