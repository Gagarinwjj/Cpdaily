package com.wisedu.cpdaily.ui.contact.teacher;


import com.wisedu.cpdaily.api.UserApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * module工厂
 */
@Module
class TeacherModule {
    private TeacherContract.View mView;

    TeacherModule(TeacherContract.View mView) {
        this.mView = mView;
    }

    @Provides
    TeacherContract.View provideView() {
        return mView;
    }

    @Provides
    UserApi provideUserApi(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }
}
