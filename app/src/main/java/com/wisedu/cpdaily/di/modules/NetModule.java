package com.wisedu.cpdaily.di.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.wisedu.cpdaily.BuildConfig;
import com.wisedu.cpdaily.Constants;
import com.wisedu.cpdaily.utils.CookieJarManager;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络Module
 * Created by wjj on 2017/7/3 15:50.
 */
@Module
public class NetModule {
    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 40 * 1024 * 1024; // 40 MiB
        File file = new File(application.getCacheDir(), "responseCache");
        return new Cache(file, cacheSize);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder newBuilder = chain.request().newBuilder();
                newBuilder.addHeader("tenantId", "wisedu");//金智大学
                newBuilder.addHeader("clientType", "cpdaily_student");
                Request newRequest = newBuilder.build();
                return chain.proceed(newRequest);
            }
        };

        Interceptor parameterInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                HttpUrl newHttpUrl = chain.request().url().newBuilder()
                        .addQueryParameter("para1", "para1")
                        .build();
                Request newRequest = chain.request().newBuilder().url(newHttpUrl).build();
                return chain.proceed(newRequest);
            }
        };

        return new OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                //.addInterceptor(parameterInterceptor)
                //.addInterceptor(cacheInterceptor)
                //.addNetworkInterceptor(cacheNetWorkInterceptor)
                .addInterceptor(new LoggingInterceptor.Builder()
                        .loggable(BuildConfig.DEBUG)
                        .setLevel(Level.BASIC)
                        .log(Platform.INFO)
                        .request("RetrofitRequest")
                        .response("RetrofitResponse")
                        .addHeader("RetrofitHeader", BuildConfig.VERSION_NAME)
                        .build())
                .cache(cache)
                .cookieJar(new CookieJarManager())
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.URL.BASE_URL)
                .build();
    }
}
