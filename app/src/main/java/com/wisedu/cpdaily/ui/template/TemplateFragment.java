package com.wisedu.cpdaily.ui.template;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wisedu.cpdaily.R;
import com.wisedu.cpdaily.base.BaseFragment;

import javax.inject.Inject;


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
        //1、使用构造函数注入
        //presenter=new TemplatePresenter(this);
        //2、使用Dagger2注入
        DaggerTemplateComponent
                .builder()
                .netComponent(mBaseNetComponent)//为了拿到UserApi，如果不用Dagger2注入，可以放到基类中
                .templateModule(new TemplateModule(this))//为了拿到View对象,如果不用Dagger2注入，可以在构造函数中注入
                .build()
                .inject(this);
        mBasePresenter = presenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
