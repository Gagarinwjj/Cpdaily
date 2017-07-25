package com.wisedu.cpdaily.ui.contact.find;

import android.os.Bundle;

import com.wisedu.cpdaily.R;
import com.wisedu.cpdaily.base.BaseActivity;


/**
 * 通讯录首页，我的关注
 */
public class FindActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        FindFragment findFragment = (FindFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);
        if (findFragment == null) {
            findFragment = FindFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.container, findFragment)
                    .commit();
        }
    }
}
