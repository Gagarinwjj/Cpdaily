package com.wisedu.cpdaily.ui.contact.teacher;


import com.wisedu.cpdaily.base.IBasePresenter;
import com.wisedu.cpdaily.base.IBaseView;
import com.wisedu.cpdaily.model.DepartVo;
import com.wisedu.cpdaily.model.TeacherVo;

import java.util.List;

/**
 * 约束
 */

interface TeacherContract {
    interface View extends IBaseView {
        void showDeportVo(List<DepartVo> deportVoList);

        void showTeacher(List<TeacherVo> teacherVoList);
    }

    interface Presenter extends IBasePresenter<View> {
        void getDeportVo(String departId);

        void getTeacher(String departId, int offset);
    }
}
