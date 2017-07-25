package com.wisedu.cpdaily.base;

import android.support.annotation.StringRes;

/**
 * 通用界面方法
 * Created by wjj on 2017/7/10 21:14.
 */

public interface IBaseView {
    void toast(String msg);

    void toast(@StringRes int resId);
}
