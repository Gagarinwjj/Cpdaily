package com.wisedu.cpdaily.ui.contact.find;


import com.wisedu.cpdaily.api.UserApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * module工厂
 */
@Module
class FindModule {
    private FindContract.View mView;

    FindModule(FindContract.View mView) {
        this.mView = mView;
    }

    @Provides
    FindContract.View provideView() {
        return mView;
    }

    @Provides
    UserApi provideUserApi(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }
}
