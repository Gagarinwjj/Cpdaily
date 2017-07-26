package com.wisedu.cpdaily.ui.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.Toast;

import com.wisedu.cpdaily.BuildConfig;
import com.wisedu.cpdaily.R;
import com.wisedu.cpdaily.base.BaseActivity;
import com.wisedu.cpdaily.model.DepartVo;
import com.wisedu.cpdaily.ui.contact.classmate.ClassmateFragment;
import com.wisedu.cpdaily.ui.contact.find.FindFragment;
import com.wisedu.cpdaily.ui.contact.school.SchoolFragment;
import com.wisedu.cpdaily.ui.contact.search.SearchFragment;
import com.wisedu.cpdaily.ui.contact.teacher.TeacherFragment;


/**
 * 单个Fragment的容器
 */
public class ContainerActivity extends BaseActivity {
    public static final String FRAGMENT_NAME = "FRAGMENT_NAME";
    private Fragment fragment;

    public static Intent getIntent(Context context, Class fragmentClass) {
        return new Intent(context, ContainerActivity.class)
                .putExtra(FRAGMENT_NAME, fragmentClass.getName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        if (getSupportFragmentManager().findFragmentById(R.id.container) == null) {
            String fragmentName = getIntent().getStringExtra(FRAGMENT_NAME);
            if (TextUtils.isEmpty(fragmentName)) {
                if (BuildConfig.DEBUG) {
                    Toast.makeText(this, "没有指定FRAGMENT_NAME", Toast.LENGTH_SHORT).show();
                }
                finish();
                return;
            }
            //在这里进行统一维护，Android中枚举不建议使用，所以不用枚举
            if (fragmentName.equalsIgnoreCase(FindFragment.class.getName())) {
                fragment = FindFragment.newInstance();
            } else if (fragmentName.equalsIgnoreCase(ClassmateFragment.class.getName())) {
                fragment = ClassmateFragment.newInstance();
            } else if (fragmentName.equalsIgnoreCase(SchoolFragment.class.getName())) {
                fragment = SchoolFragment.newInstance();
            } else if (fragmentName.equalsIgnoreCase(TeacherFragment.class.getName())) {
                DepartVo departVo = getIntent().getParcelableExtra(TeacherFragment.DEPARTMENT);
                fragment = TeacherFragment.newInstance(departVo);
            } else if (fragmentName.equalsIgnoreCase(SearchFragment.class.getName())) {
                String searchType = getIntent().getStringExtra(SearchFragment
                        .SEARCH_TYPE);
                fragment = SearchFragment.newInstance(searchType);
            }
            if (fragment == null) {
                if (BuildConfig.DEBUG) {
                    Toast.makeText(this, "没有对应的Fragment", Toast.LENGTH_SHORT).show();
                }
                finish();
                return;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment)
                    .commit();
        }
    }
}
