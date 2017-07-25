package com.wisedu.cpdaily.ui.template;


import com.wisedu.cpdaily.base.IBasePresenter;
import com.wisedu.cpdaily.base.IBaseView;

/**
 * 约束
 */

interface TemplateContract {
    interface View extends IBaseView {

    }

    interface Presenter extends IBasePresenter<View> {

    }
}
