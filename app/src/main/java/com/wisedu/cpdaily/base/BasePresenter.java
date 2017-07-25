package com.wisedu.cpdaily.base;

import android.support.annotation.NonNull;

import com.wisedu.cpdaily.model.Wrapper;
import com.wisedu.cpdaily.model.WrapperFun;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * P的具体实现，以便继承
 * 基类实现通用业务方法
 * Created by wjj on 2017/7/10 21:49.
 */

public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {
    protected V mView;//依赖注入进来
    private CompositeDisposable composite = new CompositeDisposable();

    @Override
    public void attachView(@NonNull V v) {
        mView = v;
    }

    @Override
    public void detachView() {
        composite.clear();
        mView = null;
    }

    public <T> void makeRequest(Observable<Wrapper<T>> observable, BaseObserver<T>
            observer) {
        composite.add(observable//可以结合compose封装
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable)
                            throws Exception {//todo UI进度提示

                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread()) // 指定主线程
                .observeOn(AndroidSchedulers.mainThread())
                .map(new WrapperFun<T>())
                .subscribeWith(observer));
    }
}
