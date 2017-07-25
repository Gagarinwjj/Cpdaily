package com.wisedu.cpdaily.ui.contact.search;


import com.wisedu.cpdaily.api.UserApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * module工厂
 */
@Module
class SearchModule {
    private SearchContract.View mView;

    SearchModule(SearchContract.View mView) {
        this.mView = mView;
    }

    @Provides
    SearchContract.View provideView() {
        return mView;
    }

    @Provides
    UserApi provideUserApi(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }
}
