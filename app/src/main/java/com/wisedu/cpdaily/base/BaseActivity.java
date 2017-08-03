/*
 * Copyright (c) 2017. wisedu.com
 */

package com.wisedu.cpdaily.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.wisedu.cpdaily.utils.ActivityManager;
import com.wisedu.cpdaily.utils.StatusBarUtils;


/**
 * V的具体实现，以便继承
 * 基类实现通用界面方法
 * Created by wjj on 2017/7/10 20:43.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setStatusBarTrans(this);
        StatusBarUtils.setStatusBarMode(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ActivityManager.getInstance().addActivity(this);
        //setContentViewId();
    }

    //public abstract void setContentViewId();

    @Override
    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }


}
