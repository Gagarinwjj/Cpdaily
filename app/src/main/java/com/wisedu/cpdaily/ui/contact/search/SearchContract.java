package com.wisedu.cpdaily.ui.contact.search;


import com.wisedu.cpdaily.base.IBasePresenter;
import com.wisedu.cpdaily.base.IBaseView;
import com.wisedu.cpdaily.model.TeacherVo;
import com.wisedu.cpdaily.model.UserComplete;

import java.util.List;

/**
 * 约束
 */

interface SearchContract {
    interface View extends IBaseView {
        void showClassmates(List<UserComplete> userCompleteList);

        void showTeacher(List<TeacherVo> teacherVoList);
    }

    interface Presenter extends IBasePresenter<View> {
        void getClassmates(String keyword, int offset);

        void getTeacher(String keyword, int offset);
    }
}
