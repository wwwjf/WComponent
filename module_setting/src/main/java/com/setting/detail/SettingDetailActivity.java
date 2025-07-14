package com.setting.detail;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.business.mvp.BaseActivity;
import com.setting.R;
import com.setting.export_setting.constant.ARouterPathSetting;

@Route(path = ARouterPathSetting.SETTING_DETAIL_ACTIVITY)
public class SettingDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_detail);

    }
}