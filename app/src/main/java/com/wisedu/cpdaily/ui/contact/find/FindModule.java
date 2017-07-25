package com.wisedu.cpdaily.ui.contact.find;


import dagger.Module;
import dagger.Provides;

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

}
