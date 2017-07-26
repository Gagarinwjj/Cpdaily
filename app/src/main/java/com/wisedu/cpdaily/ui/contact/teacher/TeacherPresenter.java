package com.wisedu.cpdaily.ui.contact.teacher;

import android.support.annotation.NonNull;

import com.wisedu.cpdaily.api.UserApi;
import com.wisedu.cpdaily.base.BaseObserver;
import com.wisedu.cpdaily.base.BasePresenter;
import com.wisedu.cpdaily.model.DepartVo;
import com.wisedu.cpdaily.model.TeacherVo;

import java.util.List;

import javax.inject.Inject;

/**
 * 业务实现
 */

class TeacherPresenter extends BasePresenter<TeacherContract.View> implements TeacherContract
        .Presenter {
    @Inject
    UserApi mUserApi;

    @Inject
    TeacherPresenter(@NonNull TeacherContract.View view) {
        mBaseView = view;
    }

    @Override
    public void getDeportVo(String departId) {
        makeRequest(mUserApi.getDeportVo(departId), new BaseObserver<List<DepartVo>>() {
            @Override
            public void onNextDo(List<DepartVo> deportVos) {
                mBaseView.showDeportVo(deportVos);
            }
        });
    }

    @Override
    public void getTeacher(String departId, int offset) {
        makeRequest(mUserApi.getTeacher(null, departId, 1000, offset), new
                BaseObserver<List<TeacherVo>>() {
                    @Override
                    public void onNextDo(List<TeacherVo> teacherVoList) {
                        mBaseView.showTeacher(teacherVoList);
                    }
                });
    }
}
