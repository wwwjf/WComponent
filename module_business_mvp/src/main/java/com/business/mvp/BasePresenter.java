package com.business.mvp;

import com.base.mvp.presenter.BaseMvpPresenter;
import com.base.mvp.view.IMvpView;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public abstract class BasePresenter<T extends IMvpView> extends BaseMvpPresenter<T> {

    private CompositeDisposable compositeDisposable;

    public BasePresenter(T view) {
        super(view);
        compositeDisposable = new CompositeDisposable();
    }

    /**
     * 添加订阅
     *
     * @param disposables 订阅
     */
    public void addSubscription(Disposable... disposables) {
        compositeDisposable.addAll(disposables);
    }

    /**
     * 移除订阅
     */
    public void removeSubscription() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
            compositeDisposable.clear();
        }
    }

    @Override
    public void onDestroy() {
        removeSubscription();
        super.onDestroy();
    }
}