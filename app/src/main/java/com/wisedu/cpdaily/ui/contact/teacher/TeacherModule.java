package com.wisedu.cpdaily.ui.contact.teacher;


import dagger.Module;
import dagger.Provides;

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
}
