package com.network.retrofit_rxjava_two.helper;

import android.os.Environment;

import java.io.File;

public class HttpConfig {
    public static final File DIR_CACHE_FILE = new File(Environment.getRootDirectory().getAbsolutePath());
    public static final long HTTP_TIME_OUT_TIME = 15;
}
