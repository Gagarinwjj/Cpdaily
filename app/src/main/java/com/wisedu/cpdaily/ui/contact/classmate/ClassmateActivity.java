package com.wisedu.cpdaily.ui.contact.classmate;

import android.os.Bundle;

import com.wisedu.cpdaily.R;
import com.wisedu.cpdaily.base.BaseActivity;


/**
 * 容器
 */
public class ClassmateActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        ClassmateFragment classmateFragment = (ClassmateFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);
        if (classmateFragment == null) {
            classmateFragment = ClassmateFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.container, classmateFragment)
                    .commit();
        }
    }
}
