package com.mine;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.base.router.constant.ARouterPath;
import com.business.data.bean.VersionMsgBean;
import com.business.mvp.BaseActivity;
import com.common.utils.ToastUtil;
import com.setting.export_setting.constant.ARouterPathSetting;
import com.setting.export_setting.service.ISettingService;

@Route(path = ARouterPath.MINE_ACTIVITY)
//@ViewInject(contentViewId = R.layout.activity_mine)
public class MineActivity extends BaseActivity implements IMineContract.IView {

    private MinePresenter mMinePresenter;

    @Autowired(name = ARouterPathSetting.MODULE_SETTING_SERVICE)
    ISettingService ISettingService;
    @Autowired
    ISettingService ISettingService1;
    ISettingService ISettingService2;
    ISettingService ISettingService3;


    @Override
    protected void initText() {
        mTvTitle.setText("我的主页");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine;
    }


    @Override
    public void onCreateBaseActivity(@Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        this.dismissLoading();
        mMinePresenter = new MinePresenter(this);
        initData();
        initListener();
    }


    private void initData() {


    }

    private void initListener() {
        if (ISettingService2 != null) {
            ISettingService2 = ARouter.getInstance().navigation(ISettingService.class);
        }
        if (ISettingService3 != null) {
            ISettingService3 = (ISettingService) ARouter.getInstance().build(ARouterPathSetting.MODULE_SETTING_SERVICE).navigation();
        }
        findViewById(R.id.buttonGetSetting).setOnClickListener(v -> {
            if (ISettingService != null) {
                getSettingModuleInfoSuccess(ISettingService.getSetting());
            }
            if (ISettingService1 != null) {
                getSettingModuleInfoSuccess(ISettingService1.isOpenNotification());
            }
            if (ISettingService2 != null) {
                getSettingModuleInfoSuccess(ISettingService2.getSetting());
            }
            if (ISettingService3 != null) {
                getSettingModuleInfoSuccess(ISettingService3.isOpenNotification());
            }

        });

        findViewById(R.id.buttonCheckVersion).setOnClickListener(v -> {
            mMinePresenter.checkVersion();
        });
    }


    @Override
    public void checkVersionSuccess(VersionMsgBean versionMsgBean) {
        ToastUtil.showCenter(this,
                versionMsgBean.getCnMsg() +
                        versionMsgBean.getvName());
    }

    @Override
    public void requestDataFailed(String message) {
        ToastUtil.showCenter(this, message);
    }

    @Override
    public void getSettingModuleInfoSuccess(Object object) {
        Toast.makeText(this, object.toString(), Toast.LENGTH_SHORT).show();
    }
}