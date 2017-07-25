package com.wisedu.cpdaily.ui.contact.school;


import com.wisedu.cpdaily.api.UserApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * module工厂
 */
@Module
class SchoolModule {
    private SchoolContract.View mView;

    SchoolModule(SchoolContract.View mView) {
        this.mView = mView;
    }

    @Provides
    SchoolContract.View provideView() {
        return mView;
    }

    @Provides
    UserApi provideUserApi(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }
}
