package com.common.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备工具箱，提供与设备硬件相关的工具方法
 */
public class DeviceUtil {

    /**
     * 获取屏幕尺寸
     */
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static Point getScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            return new Point(display.getWidth(), display.getHeight());
        } else {
            Point point = new Point();
            display.getSize(point);
            return point;
        }
    }

    public static String getClientInfo(Context context) {
        return StringUtil.format("{0}_{1}", getUserAgent2(context), getPackageName());
    }

    /**
     * 获取系统信息作为user-agent
     *
     * @return
     */
    public static String getUserAgent() {

        String property = System.getProperty("http.agent");
        return property + " ";
    }

    public static String getUserAgent2(Context context) {
        String userAgent = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                userAgent = WebSettings.getDefaultUserAgent(context.getApplicationContext());
            } catch (Exception e) {
                userAgent = System.getProperty("http.agent");
            }
        } else {
            userAgent = System.getProperty("http.agent");
        }
        StringBuilder sb = new StringBuilder();

        int length = 0;
        if (userAgent != null) {
            length = userAgent.length();
        }
        for (int i = 0; i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static long getLocalVersionCode() {
        long localVersion = 0;
        /*try {
            PackageInfo packageInfo = EPayApp.getInstance().getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(EPayApp.getInstance().getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }*/
        return localVersion;
    }

    public static String getLocalVersionName() {
        String localVersion = "";
        /*try {
            PackageInfo packageInfo = EPayApp.getInstance().getPackageManager()
                    .getPackageInfo(EPayApp.getInstance().getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }*/
        return localVersion;
    }

    public static String getPackageName() {
        String packageName = "";
        /*try {
            PackageInfo packageInfo = EPayApp.getInstance().getPackageManager()
                    .getPackageInfo(EPayApp.getInstance().getPackageName(), 0);
            packageName = packageInfo.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }*/
        return packageName;
    }

    // 是否支持指纹
    public static Map<Boolean, String> supportFingerprint() {
        Map<Boolean, String> resultMap = new HashMap<>();
        /*if (Build.VERSION.SDK_INT < 23) {
            resultMap.put(false, ResourceValuesUtil.getString(EPayApp.getInstance(), "epay.android.finger.tip1"));
            return resultMap;
        }
        KeyguardManager keyguardManager = EPayApp.getInstance().getSystemService(KeyguardManager.class);
        FingerprintManager fingerprintManager = EPayApp.getInstance().getSystemService(FingerprintManager.class);
        if (fingerprintManager == null) {
            resultMap.put(false, ResourceValuesUtil.getString(EPayApp.getInstance(), "epay.android.finger.tip2"));
            return resultMap;
        }
        if (!fingerprintManager.isHardwareDetected()) {
            resultMap.put(false, ResourceValuesUtil.getString(EPayApp.getInstance(), "epay.android.finger.tip3"));
            return resultMap;
        }
        if (keyguardManager == null) {
            resultMap.put(false, ResourceValuesUtil.getString(EPayApp.getInstance(), "epay.android.finger.tip4"));
            return resultMap;
        }
        if (!keyguardManager.isKeyguardSecure()) {
            resultMap.put(false, ResourceValuesUtil.getString(EPayApp.getInstance(), "epay.android.finger.tip5"));
            return resultMap;
        }
        if (!fingerprintManager.hasEnrolledFingerprints()) {
            resultMap.put(false, ResourceValuesUtil.getString(EPayApp.getInstance(), "epay.android.finger.tip6"));
            return resultMap;
        }*/
        resultMap.put(true, "指纹功能");
        return resultMap;
    }

    public static boolean isAvailable(Context context, String packageName) {
        // 获取packagemanager
        final PackageManager packageManager = context.getApplicationContext().getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        // 用于存储所有已安装程序的包名
        List<String> pName = new ArrayList<>();
        // 从pinfo中将包名字取出
        for (int i = 0; i < pinfo.size(); i++) {
            String pf = pinfo.get(i).packageName;
            pName.add(pf);
        }
        // 判断pName中是否有目标程序的包名，有true，没有false
        return pName.contains(packageName);
    }

    public static void launchAppDetail(Context context, String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) {
                return;
            }

            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}