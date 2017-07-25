package com.wisedu.cpdaily.ui.contact.school;

import android.os.Bundle;

import com.wisedu.cpdaily.R;
import com.wisedu.cpdaily.base.BaseActivity;


/**
 * 容器
 */
public class SchoolActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        SchoolFragment schoolFragment = (SchoolFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);
        if (schoolFragment == null) {
            schoolFragment = SchoolFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.container, schoolFragment)
                    .commit();
        }
    }
}
