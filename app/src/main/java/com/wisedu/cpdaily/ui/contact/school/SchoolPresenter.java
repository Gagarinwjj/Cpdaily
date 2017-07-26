package com.wisedu.cpdaily.ui.contact.school;

import android.support.annotation.NonNull;

import com.wisedu.cpdaily.api.UserApi;
import com.wisedu.cpdaily.base.BaseObserver;
import com.wisedu.cpdaily.base.BasePresenter;
import com.wisedu.cpdaily.model.UserComplete;

import java.util.List;

import javax.inject.Inject;

/**
 * 业务实现
 */

class SchoolPresenter extends BasePresenter<SchoolContract.View> implements SchoolContract
        .Presenter {
    @Inject
    UserApi mUserApi;

    @Inject
    SchoolPresenter(@NonNull SchoolContract.View view) {
        mBaseView = view;
    }

    @Override
    public void getCampusMedia() {
        makeRequest(mUserApi.getCampusMedia(), new BaseObserver<List<UserComplete>>() {
            @Override
            public void onNextDo(List<UserComplete> userCompleteList) {
                mBaseView.showCampusMedia(userCompleteList);
            }
        });
    }
}
