package com.wisedu.cpdaily.ui.template;

import android.support.annotation.NonNull;

import com.wisedu.cpdaily.api.UserApi;
import com.wisedu.cpdaily.base.BasePresenter;

import javax.inject.Inject;

/**
 * 业务实现
 */

class TemplatePresenter extends BasePresenter<TemplateContract.View> implements TemplateContract
        .Presenter {
    private UserApi mUserApi;

    @Inject
    TemplatePresenter(@NonNull TemplateContract.View view, UserApi userApi) {
        mView = view;
        mUserApi = userApi;
    }
}
