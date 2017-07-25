package com.wisedu.cpdaily.ui.contact.find;

import android.support.annotation.NonNull;

import com.wisedu.cpdaily.Constants;
import com.wisedu.cpdaily.api.UserApi;
import com.wisedu.cpdaily.base.BaseObserver;
import com.wisedu.cpdaily.base.BasePresenter;
import com.wisedu.cpdaily.model.Discover;
import com.wisedu.cpdaily.model.Statistic;
import com.wisedu.cpdaily.model.UserComplete;

import java.util.List;

import javax.inject.Inject;

/**
 * 通讯录首页，我的关注，业务实现
 * Created by wjj on 2017/7/21 09:55.
 */

class FindPresenter extends BasePresenter<FindContract.View> implements FindContract.Presenter {
    private UserApi mUserApi;

    @Inject
    FindPresenter(@NonNull FindContract.View view, UserApi userApi) {
        mView = view;
        mUserApi = userApi;
    }

    @Override
    public void getDiscover() {
        makeRequest(mUserApi.getDiscover(), new BaseObserver<Discover>() {
            @Override
            public void onNextDo(Discover discover) {
                mView.showDiscover(discover);
            }
        });
    }

    @Override
    public void getFollowers(int offset) {
        makeRequest(mUserApi.getFollowers(Constants.USER_ID, Constants
                .PAGE.LIMIT, offset), new BaseObserver<List<UserComplete>>() {
            @Override
            public void onNextDo(List<UserComplete> userCompleteList) {
                mView.showFollowers(userCompleteList);
            }
        });
    }

    @Override
    public void getStatistic() {
        makeRequest(mUserApi.getStatistic(Constants.USER_ID), new
                BaseObserver<Statistic>() {
                    @Override
                    public void onNextDo(Statistic statistic) {
                        mView.showStatistic(statistic);
                    }
                });
    }
}
