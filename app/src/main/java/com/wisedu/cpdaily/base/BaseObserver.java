package com.wisedu.cpdaily.base;

import android.text.TextUtils;
import android.widget.Toast;

import com.wisedu.cpdaily.BuildConfig;
import com.wisedu.cpdaily.Constants;
import com.wisedu.cpdaily.CpdailyApplication;

import io.reactivex.observers.DisposableObserver;


/**
 * Subscriber的处理封装
 * Created by wjj on 2017/4/7 16:25.
 */

public abstract class BaseObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T t) {
        onNextDo(t);
    }

    @Override
    public void onError(Throwable t) {
        if (Constants.RESPONSE.RE_LOGIN.equalsIgnoreCase(t.getMessage())) {//特殊异常处理
            Toast.makeText(CpdailyApplication.getInstance(), t.getMessage(), Toast.LENGTH_SHORT)
                    .show();
        } else {//普通异常处理
            if (BuildConfig.DEBUG) {
                Toast.makeText(CpdailyApplication.getInstance(), t.getMessage(), Toast
                        .LENGTH_SHORT).show();
            } else {
                String msg = t.getMessage();
                if (!TextUtils.isEmpty(msg) && msg.startsWith(Constants.RESPONSE.API_ERROR)) {
                    msg = msg.substring(Constants.RESPONSE.API_ERROR.length(), msg.length());
                    Toast.makeText(CpdailyApplication.getInstance(), msg, Toast.LENGTH_SHORT)
                            .show();
                }
            }
        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void onNextDo(T t);
}
