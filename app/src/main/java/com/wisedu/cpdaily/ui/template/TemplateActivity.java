package com.wisedu.cpdaily.ui.template;

import android.os.Bundle;

import com.wisedu.cpdaily.R;
import com.wisedu.cpdaily.base.BaseActivity;


/**
 * 容器
 */
public class TemplateActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        TemplateFragment templateFragment = (TemplateFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);
        if (templateFragment == null) {
            templateFragment = TemplateFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.container, templateFragment)
                    .commit();
        }
    }
}
