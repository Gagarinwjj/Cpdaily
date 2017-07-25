package com.wisedu.cpdaily.ui.contact.classmate;


import com.wisedu.cpdaily.api.UserApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * module工厂
 */
@Module
class ClassmateModule {
    private ClassmateContract.View mView;

    ClassmateModule(ClassmateContract.View mView) {
        this.mView = mView;
    }

    @Provides
    ClassmateContract.View provideView() {
        return mView;
    }

    @Provides
    UserApi provideUserApi(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }
}
