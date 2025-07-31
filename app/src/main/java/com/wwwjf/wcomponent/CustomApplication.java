package com.wwwjf.wcomponent;

import com.business.BaseMvvmApplication;
import com.common.application.IApplicationInit;
import com.common.application.ModuleConfig;
import com.common.log.KLog;
import com.common.utils.NetworkUtil;


public class CustomApplication extends BaseMvvmApplication {

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.isModule){
            return;
        }
        initModuleHighPriority();
    }

    private void initModuleHighPriority() {
        try {
            KLog.e("===============size="+ModuleConfig.moduleList.length+"===========");
            //遍历所有module的Application
            for (String module : ModuleConfig.moduleList) {
                Class<?> moduleClass = Class.forName(module);
                Object newInstance = moduleClass.newInstance();
                if (!(newInstance instanceof IApplicationInit)) {
                    continue;
                }
                ((IApplicationInit) newInstance).onInitHighPriority(this);
            }
            KLog.e("==========================");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
