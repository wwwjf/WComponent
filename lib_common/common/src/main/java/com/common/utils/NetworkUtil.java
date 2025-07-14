package com.common.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.text.TextUtils;

import com.common.log.KLog;


public class NetworkUtil {

    /**
     * 网络不可用
     */
    public static final int NO_NET_WORK = 0;
    /**
     * 是wifi连接
     */
    public static final int WIFI = 1;
    /**
     * 不是wifi连接
     */
    public static final int NO_WIFI = 2;

    private static Context mContext;

    private NetworkUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void init(Context context) {
        mContext = context;
    }


    /**
     * 判断是否打开网络
     *
     * @param context context
     * @return boolean
     */
    public static boolean isNetWorkAvailable(Context context) {
        if (context == null || context.getApplicationContext() == null) {
            return false;
        }
        boolean isAvailable = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    /**
     * 获取网络类型
     *
     * @param context context
     * @return int
     */
    public static int getNetWorkType(Context context) {
        if (!isNetWorkAvailable(context)) {
            return NetworkUtil.NO_NET_WORK;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo != null && networkInfo.isConnectedOrConnecting())
            return NetworkUtil.WIFI;
        else
            return NetworkUtil.NO_WIFI;
    }

    /**
     * 判断当前网络是否为wifi
     *
     * @param context context
     * @return 如果为wifi返回true；否则返回false
     */
    public static boolean isWiFiConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 判断MOBILE网络是否可用
     *
     * @param context context
     * @return 如果可用返回true；否则返回false
     */
    public static boolean isMobileDataEnable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isMobileDataEnable = false;
        if (manager == null) {
            return isMobileDataEnable;
        }
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (networkInfo == null) {
            return isMobileDataEnable;
        }
        isMobileDataEnable = networkInfo.isConnectedOrConnecting();
        return isMobileDataEnable;
    }

    /**
     * 判断wifi 是否可用
     *
     * @param context context
     * @return boolean
     */
    public static boolean isWifiDataEnable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiDataEnable = false;
        if (manager == null) {
            return isWifiDataEnable;
        }
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo == null) {
            return isWifiDataEnable;
        }
        isWifiDataEnable = networkInfo.isConnectedOrConnecting();
        return isWifiDataEnable;
    }

    /**
     * 判断有线网络是否可用
     *
     * @param context context
     * @return boolean
     */
    public static boolean isEthernetEnable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isEthernetEnable = false;
        if (manager == null) {
            return isEthernetEnable;
        }
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
        if (networkInfo == null) {
            return isEthernetEnable;
        }
        return networkInfo.isConnected();
    }

    public static void showDialog(Activity activity) {
//        new XPopup.Builder(activity).asCustom(new NetworkErrorDialog(activity)).show();
    }

    /**
     * 跳转到网络设置页面
     *
     * @param activity activity
     */
    public static void GoSetting(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        activity.startActivity(intent);
    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
        intent.setComponent(cn);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }

    public static boolean getNetworkStatus(Context context) {
        return isNetWorkAvailable(context) &&
                (isMobileDataEnable(context) || isWifiDataEnable(context) || isEthernetEnable(context));
    }

    /**
     * 获取当前连接WIFI的SSID
     * 9.0以后需要定位权限
     */
    public static String getConnectWifiSsid(Context context) {
        if (context == null) {
            return "";
        }
        String wifiName = null;
        ConnectivityManager connectManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            wifiName = networkInfo.getExtraInfo();
        }
        if (wifiName == null || TextUtils.isEmpty(wifiName) || wifiName.contains("unknown")) {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (wifiManager != null) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                wifiName = connectionInfo.getSSID();
                String bssid = connectionInfo.getBSSID();
                KLog.e("bssid=" + bssid + ",wifiName=" + wifiName);
            }
        }
        return wifiName;
    }

    /**
     * 判断网络是否可用,需要在application中初始化init
     *
     * @return boolean true:可用；false:不可用
     */
    public static boolean isConnected() {
        if (!checkContextIsNull()) {
            return false;
        }
        return getNetworkStatus(mContext);
    }

    private static boolean checkContextIsNull() {
        if (mContext == null) {
            throw new NullPointerException("请先调用init方法初始化");
        }
        return true;
    }
}