package com.wisedu.cpdaily.ui.contact.classmate;


import dagger.Module;
import dagger.Provides;

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
}
