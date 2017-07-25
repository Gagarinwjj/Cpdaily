package com.wisedu.cpdaily.utils;


import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.wisedu.cpdaily.BuildConfig;
import com.wisedu.cpdaily.Constants;
import com.wisedu.cpdaily.CpdailyApplication;
import com.wisedu.cpdaily.base.BaseObserver;
import com.wisedu.cpdaily.model.Wrapper;
import com.wisedu.cpdaily.model.WrapperFun;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 服务器接口帮助类
 */
public class ServiceHelper {

    private static ServiceHelper mInstance;
    private static Retrofit retrofit;

    private ServiceHelper() {
        int cacheSize = 40 * 1024 * 1024; // 40 MiB
        File file = new File(CpdailyApplication.getInstance().getCacheDir(), "responseCache");
        Cache responseCache = new Cache(file, cacheSize);

        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder newBuilder = chain.request().newBuilder();
                newBuilder.addHeader("tenantId", "wisedu");
                Request newRequest = newBuilder.build();
                return chain.proceed(newRequest);
            }
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(new LoggingInterceptor.Builder()
                        .loggable(BuildConfig.DEBUG)
                        .setLevel(Level.BASIC)
                        .log(Platform.INFO)
                        .request("RetrofitRequest")
                        .response("RetrofitResponse")
                        .addHeader("RetrofitHeader", BuildConfig.VERSION_NAME)
                        .build())
                .cache(responseCache)
                .cookieJar(new CookieJarManager())
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

        OkHttpClient client = builder.build();

        retrofit = new Retrofit.Builder().client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.URL.BASE_URL)
                .build();
    }


    public static ServiceHelper getInstance() {
        if (mInstance == null) {
            synchronized (ServiceHelper.class) {
                if (mInstance == null) {
                    mInstance = new ServiceHelper();
                }
            }
        }
        return mInstance;
    }


    /**
     * 根据泛型返回服务实例
     *
     * @param service 服务类
     * @param <T>     服务泛型
     * @return 服务实例
     */
    public <T> T getService(Class<T> service) {
        return retrofit.create(service);
    }

    public <T> void makeRequest(Observable<Wrapper<T>> observable, BaseObserver<T>
            observer) {
        observable//可以结合compose封装
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable)
                            throws Exception {

                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread()) // 指定主线程
                .observeOn(AndroidSchedulers.mainThread())
                .map(new WrapperFun<T>())
                .subscribeWith(observer);
    }
}
