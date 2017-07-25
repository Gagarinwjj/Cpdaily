package com.wisedu.cpdaily.ui.contact.teacher;

import android.os.Bundle;

import com.wisedu.cpdaily.R;
import com.wisedu.cpdaily.base.BaseActivity;
import com.wisedu.cpdaily.model.DepartVo;


/**
 * 容器
 */
public class TeacherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        TeacherFragment teacherFragment = (TeacherFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);
        if (teacherFragment == null) {
            DepartVo departVo = getIntent().getParcelableExtra(TeacherFragment.DEPARTMENT);
            teacherFragment = TeacherFragment.newInstance(departVo);
            getSupportFragmentManager().beginTransaction().add(R.id.container, teacherFragment)
                    .commit();
        }
    }
}
