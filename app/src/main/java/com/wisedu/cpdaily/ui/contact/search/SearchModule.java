package com.wisedu.cpdaily.ui.contact.search;


import dagger.Module;
import dagger.Provides;

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
}
