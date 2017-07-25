package com.wisedu.cpdaily.ui.contact.school;


import com.wisedu.cpdaily.base.IBasePresenter;
import com.wisedu.cpdaily.base.IBaseView;
import com.wisedu.cpdaily.model.UserComplete;

import java.util.List;

/**
 * 约束
 */

interface SchoolContract {
    interface View extends IBaseView {
        void showCampusMedia(List<UserComplete> userCompleteList);
    }

    interface Presenter extends IBasePresenter<View> {
        void getCampusMedia();
    }
}
