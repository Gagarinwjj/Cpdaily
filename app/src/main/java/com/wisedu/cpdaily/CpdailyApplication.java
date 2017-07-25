package com.wisedu.cpdaily;

import android.app.Application;

import com.wisedu.cpdaily.di.components.DaggerNetComponent;
import com.wisedu.cpdaily.di.components.NetComponent;
import com.wisedu.cpdaily.di.modules.AppModule;
import com.wisedu.cpdaily.di.modules.NetModule;

/**
 * 应用程序入口
 * Created by wjj on 2017/6/20 11:22.
 */

public class CpdailyApplication extends Application {
    private static CpdailyApplication mInstance;
    private NetComponent mNetComponent;

    public static CpdailyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();
    }

    /**
     * 对外提供网络组建
     */
    public NetComponent getNetComponent() {
        return mNetComponent;
    }

}
