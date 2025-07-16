package com.business;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.base.router.BaseRouterApplication;
import com.business.utils.Utils;

public class BaseMvvmApplication extends BaseRouterApplication implements ViewModelStoreOwner {

    private ViewModelStore mAppViewModelStore;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppViewModelStore = new ViewModelStore();
        Utils.init(this);
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }
}