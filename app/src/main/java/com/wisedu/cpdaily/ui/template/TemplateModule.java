package com.wisedu.cpdaily.ui.template;


import com.wisedu.cpdaily.api.UserApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * module工厂
 */
@Module
class TemplateModule {
    private TemplateContract.View mView;

    TemplateModule(TemplateContract.View mView) {
        this.mView = mView;
    }

    @Provides
    TemplateContract.View provideView() {
        return mView;
    }

    @Provides
    UserApi provideUserApi(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }
}
