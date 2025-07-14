package com.setting.export_setting.service;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface ISettingService extends IProvider {

    String getSetting();

    boolean isOpenNotification();
}
