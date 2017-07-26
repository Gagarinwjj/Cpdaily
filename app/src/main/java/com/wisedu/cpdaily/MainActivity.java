package com.wisedu.cpdaily;

import android.os.Bundle;
import android.view.View;

import com.wisedu.cpdaily.base.BaseActivity;
import com.wisedu.cpdaily.ui.common.ContainerActivity;
import com.wisedu.cpdaily.ui.contact.find.FindFragment;

/**
 * 主界面
 * Created by wjj on 2017/06/20 10:55:23.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openContact(View view) {
        startActivity(ContainerActivity.getIntent(this, FindFragment.class));
    }
}
