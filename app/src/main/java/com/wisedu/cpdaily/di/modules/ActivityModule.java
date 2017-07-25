package com.wisedu.cpdaily.di.modules;

import android.app.Activity;

import com.wisedu.cpdaily.di.scope.ScopeActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Activity模块
 * Created by wjj on 2017/07/03 17:58:56.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ScopeActivity
    public Activity provideActivity() {
        return mActivity;
    }

}
