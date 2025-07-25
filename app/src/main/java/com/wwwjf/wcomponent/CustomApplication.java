package com.wwwjf.wcomponent;

import com.base.mvvm_two.utils.ContextUtil;
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

        ContextUtil.Companion.setContext(this);
    }
}
