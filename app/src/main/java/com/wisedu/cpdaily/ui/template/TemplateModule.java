package com.wisedu.cpdaily.ui.template;


import dagger.Module;
import dagger.Provides;

/**
 * module工厂
 */
@Module
class TemplateModule {
    private TemplateContract.View mView;

    TemplateModule(TemplateContract.View mView) {
        this.mView = mView;
    }

    @Provides
    TemplateContract.View provideView() {
        return mView;
    }

}
