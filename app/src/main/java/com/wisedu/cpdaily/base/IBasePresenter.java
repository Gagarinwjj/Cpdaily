package com.wisedu.cpdaily.base;

import android.support.annotation.NonNull;

/**
 * 通用业务方法
 * Created by wjj on 2017/7/10 21:15.
 */

public interface IBasePresenter<V extends IBaseView> {
    void attachView(@NonNull V v);//调用attachView方法注入，也可以通过依赖注入

    void detachView();
}
