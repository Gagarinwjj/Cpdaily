package com.wisedu.cpdaily.di.components;

import android.content.SharedPreferences;

import com.wisedu.cpdaily.di.modules.AppModule;
import com.wisedu.cpdaily.di.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 网络注射器
 * Created by wjj on 2017/7/3 16:23.
 */
@Singleton
@Component(modules = {NetModule.class, AppModule.class})
public interface NetComponent {
    Retrofit retrofit();

    OkHttpClient okHttpClient();

    SharedPreferences sharedPreferences();
}
