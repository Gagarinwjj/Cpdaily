package com.wisedu.cpdaily.ui.contact.find;

import com.wisedu.cpdaily.base.IBasePresenter;
import com.wisedu.cpdaily.base.IBaseView;
import com.wisedu.cpdaily.model.Discover;
import com.wisedu.cpdaily.model.Statistic;
import com.wisedu.cpdaily.model.UserComplete;

import java.util.List;

/**
 * 通讯录首页，我的关注，约束
 */

interface FindContract {
    interface View extends IBaseView {
        /**
         * 显示发现
         *
         * @param discover 发现实体
         */
        void showDiscover(Discover discover);

        /**
         * 显示粉丝列表
         *
         * @param userCompleteList 用户完整信息
         */
        void showFollowers(List<UserComplete> userCompleteList);

        /**
         * 显示统计信息
         *
         * @param statistic 统计信息
         */
        void showStatistic(Statistic statistic);
    }

    interface Presenter extends IBasePresenter<View> {
        /**
         * 获取发现
         */
        void getDiscover();

        /**
         * 我的关注
         *
         * @param page 页码，从0开始
         */
        void getFollowers(int page);

        /**
         * 个人统计
         */
        void getStatistic();
    }
}
