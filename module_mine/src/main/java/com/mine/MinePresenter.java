package com.mine;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.business.mvp.BasePresenter;
import com.setting.export_setting.constant.ARouterPathSetting;
import com.setting.export_setting.service.ISettingService;

public class MinePresenter extends BasePresenter<IMineContract.IView> implements IMineContract.IPresenter {

    private final MineModel mineModel;

    public MinePresenter(IMineContract.IView view) {
        super(view);
        mineModel = new MineModel();

    }

    @Override
    protected IMineContract.IView getEmptyView() {
        return IMineContract.emptyView;
    }

    @Override
    public void checkVersion() {
        getView().showLoading();
        addSubscription(mineModel.checkVersion()
                .subscribe(response -> {
                            getView().checkVersionSuccess(response);
                            getView().dismissLoading();
                        }, throwable -> {
                            getView().requestDataFailed(throwable.getMessage());
                            getView().dismissLoading();
                        }
                ));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
