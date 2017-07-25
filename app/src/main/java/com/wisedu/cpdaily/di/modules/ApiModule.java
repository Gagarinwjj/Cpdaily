package com.wisedu.cpdaily.di.modules;


import com.wisedu.cpdaily.api.UserApi;
import com.wisedu.cpdaily.di.scope.ScopeActivity;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * 网络接口module
 * Created by wjj on 2017/7/3 17:29.
 */
@Module
public class ApiModule {
    @Provides
    @ScopeActivity
    public UserApi provideUserApi(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }
}
