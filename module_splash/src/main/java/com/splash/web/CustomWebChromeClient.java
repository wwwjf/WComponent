package com.splash.web;

import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.common.log.KLog;

public class CustomWebChromeClient extends WebChromeClient {

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        KLog.e("webview===onJsAlert===message="+message+",result="+result.toString());
        result.cancel();
//                return super.onJsAlert(view, url, message, result);
        return true;
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        KLog.e("webview===onJsConfirm===message="+message+",result="+result.toString());
        result.cancel();
//                return super.onJsConfirm(view, url, message, result);
        return true;
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {

        KLog.e("webview==onJsPrompt====message="+message+",defaultValue="+defaultValue+",result="+result.toString());
        result.cancel();
//                return super.onJsPrompt(view, url, message, defaultValue, result);
        return true;
    }
}
