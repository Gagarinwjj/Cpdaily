package com.wisedu.cpdaily.ui.contact.search;

import android.os.Bundle;

import com.wisedu.cpdaily.R;
import com.wisedu.cpdaily.base.BaseActivity;


/**
 * 容器
 */
public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        SearchFragment searchFragment = (SearchFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);
        if (searchFragment == null) {
            searchFragment = SearchFragment.newInstance(getIntent().getStringExtra(SearchFragment
                    .SEARCH_TYPE));
            getSupportFragmentManager().beginTransaction().add(R.id.container, searchFragment)
                    .commit();
        }
    }
}
