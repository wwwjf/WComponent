package com.setting.service;

import android.content.Context;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.setting.export_setting.constant.ARouterPathSetting;
import com.setting.export_setting.service.ISettingService;

@Route(path = ARouterPathSetting.MODULE_SETTING_SERVICE)
public class ISettingServiceImpl implements ISettingService {
    private Context context;

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public String getSetting() {
        return  "返回setting信息";
    }

    @Override
    public boolean isOpenNotification() {
        return false;
    }
}
