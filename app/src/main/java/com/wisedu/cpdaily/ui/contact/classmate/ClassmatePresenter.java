package com.wisedu.cpdaily.ui.contact.classmate;

import android.support.annotation.NonNull;

import com.wisedu.cpdaily.Constants;
import com.wisedu.cpdaily.api.UserApi;
import com.wisedu.cpdaily.base.BaseObserver;
import com.wisedu.cpdaily.base.BasePresenter;
import com.wisedu.cpdaily.model.Academy;
import com.wisedu.cpdaily.model.UserComplete;

import java.util.List;

import javax.inject.Inject;

/**
 * 业务实现
 */

class ClassmatePresenter extends BasePresenter<ClassmateContract.View> implements ClassmateContract
        .Presenter {
    @Inject
    UserApi mUserApi;

    @Inject
    ClassmatePresenter(@NonNull ClassmateContract.View view) {
        mView = view;
    }

    @Override
    public void getClassmates(String grade, String academy, String major, String sex, String
            keyword, int offset) {
        makeRequest(mUserApi.getClassmates(grade, academy, major, sex, keyword, Constants.PAGE
                .LIMIT, offset), new BaseObserver<List<UserComplete>>() {
            @Override
            public void onNextDo(List<UserComplete> userCompleteList) {
                mView.showClassmates(userCompleteList);
            }
        });
    }

    @Override
    public void getAcademy() {
        makeRequest(mUserApi.getAcademy(), new BaseObserver<List<Academy>>() {
            @Override
            public void onNextDo(List<Academy> academies) {
                mView.initAcademy(academies);
            }
        });
    }
}
