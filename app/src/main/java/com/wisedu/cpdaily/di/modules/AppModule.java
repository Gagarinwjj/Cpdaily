package com.wisedu.cpdaily.di.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Application Module
 * Created by wjj on 2017/7/3 15:40.
 */
@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    Application provideMyApplication() {
        return mApplication;
    }
}
