package com.wisedu.cpdaily.ui.contact.school;


import dagger.Module;
import dagger.Provides;

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
}
