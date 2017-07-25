package com.wisedu.cpdaily.ui.contact.search;

import android.support.annotation.NonNull;

import com.wisedu.cpdaily.Constants;
import com.wisedu.cpdaily.api.UserApi;
import com.wisedu.cpdaily.base.BaseObserver;
import com.wisedu.cpdaily.base.BasePresenter;
import com.wisedu.cpdaily.model.TeacherVo;
import com.wisedu.cpdaily.model.UserComplete;

import java.util.List;

import javax.inject.Inject;

/**
 * 业务实现
 */

class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract
        .Presenter {
    @Inject
    UserApi mUserApi;

    @Inject
    SearchPresenter(@NonNull SearchContract.View view) {
        mView = view;
    }

    @Override
    public void getClassmates(String keyword, int offset) {
        makeRequest(mUserApi.getClassmates(null, null, null, null, keyword, Constants.PAGE.LIMIT,
                offset), new BaseObserver<List<UserComplete>>() {
            @Override
            public void onNextDo(List<UserComplete> userCompleteList) {
                mView.showClassmates(userCompleteList);
            }
        });
    }

    @Override
    public void getTeacher(String keyword, int offset) {
        makeRequest(mUserApi.getTeacher(keyword, null, Constants.PAGE.LIMIT, offset), new
                BaseObserver<List<TeacherVo>>() {
                    @Override
                    public void onNextDo(List<TeacherVo> teacherVoList) {
                        mView.showTeacher(teacherVoList);
                    }
                });
    }
}
