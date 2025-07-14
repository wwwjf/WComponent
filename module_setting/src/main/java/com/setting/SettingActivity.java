package com.setting;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.base.router.constant.ARouterPath;
import com.network.retrofit.OnRequestListener;
import com.setting.api.NetworkServiceManagerSetting;
import com.setting.api.data.MovieSettingBean;
import com.setting.export_setting.constant.ARouterPathSetting;

@Route(path = ARouterPathSetting.SETTING_ACTIVITY)
public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting);
        ARouter.getInstance().inject(this);

        findViewById(R.id.buttonMain).setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterPath.MAIN_ACTIVITY).navigation();
        });
        findViewById(R.id.buttonLogin).setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterPath.LOGIN_ACTIVITY).navigation();
        });
        findViewById(R.id.buttonDetail).setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterPathSetting.SETTING_DETAIL_ACTIVITY).navigation();
        });

        //retrofit请求
        NetworkServiceManagerSetting.movieDetail("index", new OnRequestListener<MovieSettingBean>() {
            @Override
            public void onResponse(MovieSettingBean entity, String msg) {

            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });

    }
}