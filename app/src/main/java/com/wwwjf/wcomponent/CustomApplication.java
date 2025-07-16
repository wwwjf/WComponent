package com.wwwjf.wcomponent;

import com.business.BaseMvvmApplication;
import com.common.utils.NetworkUtil;

public class CustomApplication extends BaseMvvmApplication {

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
