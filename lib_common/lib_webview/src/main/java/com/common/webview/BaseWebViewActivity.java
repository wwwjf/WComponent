package com.common.webview;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseWebViewActivity extends AppCompatActivity {

    private WebView mWebView;

    protected String mUrl;
    protected boolean hasNetError;

    protected VaryViewHelperController mVaryViewHelperController;

    private boolean mIsUrl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mVaryViewHelperController = new VaryViewHelperController(mWebView);

        mVaryViewHelperController.showLoading();

        initWebView();

        mIsUrl = getIntent().getBooleanExtra("isUrl", true);

        mUrl = getIntent().getStringExtra("url");

        if (null != savedInstanceState) {
            mWebView.restoreState(savedInstanceState);
        } else {
            initLoad();
        }

        initSubData();
    }


    @Override
    protected void onDestroy() {
        if (null != mWebView) {
            mWebView.clearCache(true);
            mWebView.clearHistory();
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }


    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        mWebView.getSettings().setJavaScriptEnabled(true);//启用js
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//js和android交互
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        mWebView.getSettings().setAllowFileAccess(true);// 允许访问文件
        mWebView.getSettings().setSupportZoom(false);//关闭zoom按钮
        mWebView.getSettings().setBuiltInZoomControls(false);//关闭zoom
        mWebView.getSettings().setBlockNetworkImage(true);
        if (Build.VERSION.SDK_INT >= 19) {
            mWebView.getSettings().setLoadsImagesAutomatically(true);
        } else {
            mWebView.getSettings().setLoadsImagesAutomatically(false);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            mWebView.getSettings().setMixedContentMode(0);
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else if (Build.VERSION.SDK_INT >= 19) {
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else if (Build.VERSION.SDK_INT < 19) {
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        WebSettings settings = mWebView.getSettings();
        Class<?> clazz = settings.getClass();
        try {
            clazz.getMethod("setPluginsEnabled", boolean.class).invoke(
                    settings, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mWebView.addJavascriptInterface(getJsHistory(), "grapefs");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http:") || url.startsWith("https://")) {
                    view.loadUrl(url);
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (mWebView != null) {
                    if (!mWebView.getSettings().getLoadsImagesAutomatically()) {
                        mWebView.getSettings().setLoadsImagesAutomatically(true);
                    }
                    if (mWebView.getSettings().getBlockNetworkImage())
                        mWebView.getSettings().setBlockNetworkImage(false);
                }

                if (!hasNetError){
                    restoreView();

                    onPageFinish();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                hasNetError = true;
                mVaryViewHelperController.showNetworkError(v -> {
                    hasNetError = false;
                    mVaryViewHelperController.showLoading();
                    initLoad();
                });
            }
        });
    }


    protected void restoreView(){
        mVaryViewHelperController.restore();
    }

    protected void showDialogTips(String text) {

    }

    protected void dismissDialogTips() {

    }
    protected void doJsProgress(String text,String fun,String ... params){
        if (!TextUtils.isEmpty(text)){
            showDialogTips(text);
        }
        executeJs(fun,params);
    }
    private void initLoad() {
        hasNetError = false;
        Map<String, String> noCacheHeaders = new HashMap<>(2);
        noCacheHeaders.put("Pragma", "no-cache");
        noCacheHeaders.put("Cache-Control", "no-cache");
        if (mIsUrl) {
            mWebView.loadUrl(mUrl, noCacheHeaders);
        } else {
            mWebView.loadData(mUrl, "text/html;charset=utf-8", "utf-8");
        }
    }

    protected void executeJs(String fun, String... params) {
        if (mWebView != null) {
            String action;
            if (params == null || params.length == 0) {
                action = "javascript:" + fun + "()";
            } else {
                StringBuilder allParams = new StringBuilder();
                for (String param : params) {
                    allParams.append(param).append(",");
                }
                allParams.deleteCharAt(allParams.length()-1);
                action = "javascript:" + fun + "('" + allParams + "')";
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mWebView.evaluateJavascript(action, null);
            } else {
                mWebView.loadUrl(action);
            }
        }
    }

    /**
     * 网页加载成功回调
     */
    protected abstract void onPageFinish();

    protected abstract void initSubData();

    protected abstract Object getJsHistory();
}
