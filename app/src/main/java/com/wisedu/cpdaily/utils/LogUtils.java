
package com.wisedu.cpdaily.utils;

import android.util.Log;

import com.wisedu.cpdaily.BuildConfig;


/**
 * 日志工具
 * Created by wjj on 2016/08/30 14:27:59.
 */
public class LogUtils {
    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, msg);
        }
    }
}
