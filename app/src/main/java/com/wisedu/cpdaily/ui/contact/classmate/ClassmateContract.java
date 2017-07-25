package com.wisedu.cpdaily.ui.contact.classmate;


import com.wisedu.cpdaily.base.IBasePresenter;
import com.wisedu.cpdaily.base.IBaseView;
import com.wisedu.cpdaily.model.Academy;
import com.wisedu.cpdaily.model.UserComplete;

import java.util.List;

/**
 * 约束
 */

interface ClassmateContract {
    interface View extends IBaseView {
        /**
         * 显示筛选的同学列表
         *
         * @param userCompleteList 列表数据
         */
        void showClassmates(List<UserComplete> userCompleteList);

        /**
         * 初始化学院及专业
         *
         * @param academyList 学院列表
         */
        void initAcademy(List<Academy> academyList);
    }

    interface Presenter extends IBasePresenter<View> {
        /**
         * 筛选同学
         *
         * @param grade   年级
         * @param academy 学院
         * @param major   专业
         * @param sex     性别
         * @param keyword 关键字
         * @param offset  偏移量
         */
        void getClassmates(String grade, String academy, String major, String sex, String
                keyword, int offset);

        /**
         * 获取学院专业信息
         */
        void getAcademy();
    }
}
