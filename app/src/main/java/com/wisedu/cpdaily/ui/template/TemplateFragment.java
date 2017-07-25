package com.wisedu.cpdaily.ui.template;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wisedu.cpdaily.R;
import com.wisedu.cpdaily.base.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;


/**
 * 界面实现
 */
public class TemplateFragment extends BaseFragment implements TemplateContract.View {

    @Inject
    TemplatePresenter presenter;

    public TemplateFragment() {
    }

    public static TemplateFragment newInstance() {
        return new TemplateFragment();
    }

    @Override
    public void inject() {
        DaggerTemplateComponent
                .builder()
                .netComponent(mBaseNetComponent)
                .templateModule(new TemplateModule(this))
                .build()
                .inject(this);
        mBasePresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        mBaseUnBinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
