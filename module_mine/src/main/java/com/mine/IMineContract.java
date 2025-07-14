package com.mine;

import com.base.mvp.presenter.ILifeCycle;
import com.base.mvp.presenter.MvpController;
import com.base.mvp.view.IMvpView;
import com.business.data.bean.VersionMsgBean;
import com.common.utils.ToastUtil;

public interface IMineContract {

    interface IView extends IMvpView {

        void checkVersionSuccess(VersionMsgBean versionMsgBean);

        void requestDataFailed(String message);

        void showLoading();

        void dismissLoading();

        void getSettingModuleInfoSuccess(Object object);
    }

    interface IPresenter extends ILifeCycle {
        void checkVersion();
    }

    IMineContract.IView emptyView = new IView() {

        @Override
        public void checkVersionSuccess(VersionMsgBean versionMsgBean) {

        }

        @Override
        public void requestDataFailed(String message) {

        }

        @Override
        public void showLoading() {

        }

        @Override
        public void dismissLoading() {

        }

        @Override
        public void getSettingModuleInfoSuccess(Object object) {

        }

        @Override
        public MvpController getMvpController() {
            return null;
        }
    };
}