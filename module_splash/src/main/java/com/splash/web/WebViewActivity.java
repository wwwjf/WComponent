package com.splash.web;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.base.router.constant.ARouterPath;
import com.common.log.KLog;
import com.splash.R;
import com.splash.databinding.ActivityWebviewBinding;

@Route(path = ARouterPath.WEB_VIEW_ACTIVITY)
public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWebviewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_webview);
        binding.buttonCallJs.setOnClickListener(v -> {
            executeJS(binding);
        });
//        binding.webView.loadUrl("https://sophiadesign.buzz/test/");
        binding.webView.loadUrl("file:///android_asset/webdemo.html");
        WebSettings webViewSettings = binding.webView.getSettings();
        webViewSettings.setJavaScriptEnabled(true);
        webViewSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        //js调用Android中的方法
        binding.webView.addJavascriptInterface(new JSToAndroid(this), "JsToAndroid");
        //Android调用js中的方法
        executeJS(binding);

        binding.webView.setWebViewClient(new CustomWebViewClient());
        binding.webView.setWebChromeClient(new CustomWebChromeClient());
    }

    private void executeJS(ActivityWebviewBinding binding) {
        //Android调用js中的方法
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            binding.webView.loadUrl("javascript:clickBtn()");
        } else {
            binding.webView.evaluateJavascript("javascript:clickBtn()", value -> KLog.e("webview======="+value));
        }
    }
}