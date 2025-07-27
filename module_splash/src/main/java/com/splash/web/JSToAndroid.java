package com.splash.web;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;

import com.common.log.KLog;
import com.common.utils.ToastUtil;

public class JSToAndroid {

    private Context context;

    public JSToAndroid(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void callAndroid(String message) {
        KLog.e("======调用Android中方法callAndroid");
        new Handler(Looper.getMainLooper()).post(() -> {
            ToastUtil.showCenter(context, message);
        });
    }
}
