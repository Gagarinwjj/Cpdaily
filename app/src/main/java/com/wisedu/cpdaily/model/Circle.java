package com.wisedu.cpdaily.model;

import java.util.List;

/**
 * 圈子
 * Created by wjj on 2017/7/21 13:47.
 */

public class Circle {
    /**
     * id : fb3cc514-2a2e-11e7-b20f-acbc327c3dc9
     * name : 新生报到
     * icon : http://img.cpdaily.com/njxz/20170109/14839516618567OyB5w2sgk7gN0yKisoa.png
     * slogan : 答疑解惑，带你迅速融入大学生活
     * dataSrcType : NORMAL
     * dynamic : 0
     * displayType : NORMAL
     * noticeSummary : 公告公告公告
     * displayItem : BANNER,CONTENT,PUBLISH_BUTTON
     * h5Url : https://www.baidu.com
     * bannerId : f0e7271e-2a2e-11e7-9d4d-acbc327c3dc9
     * circleType : PHYSICAL
     * backgroundImg : http://img.cpdaily.com/circleHead.png
     * scheduleOpenUrl : {"name":"课表","installUrl":"","openUrl":"http://wectest2.wisedu
     * .com/comapp/schoolTimetable/mobile/index.html"}
     * scheduleDataUrl : http://wectest2.wisedu
     * .com/comapp/schoolTimetable/api/todayAndTomorrowCourses.do
     * userDisplayItem : GRADE,ACADEMY,DEPARTMENT,USERTYPE,JOB
     * dataCircles : []
     * userCount : 37
     */

    private String id;
    private String name;
    private String icon;
    private String slogan;
    private String dataSrcType;
    private int dynamic;
    private String displayType;
    private String noticeSummary;
    private String displayItem;
    private String h5Url;
    private String bannerId;
    private String circleType;
    private String backgroundImg;
    private ScheduleOpenUrlEntity scheduleOpenUrl;
    private String scheduleDataUrl;
    private String userDisplayItem;
    private int userCount;
    private List<?> dataCircles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getDataSrcType() {
        return dataSrcType;
    }

    public void setDataSrcType(String dataSrcType) {
        this.dataSrcType = dataSrcType;
    }

    public int getDynamic() {
        return dynamic;
    }

    public void setDynamic(int dynamic) {
        this.dynamic = dynamic;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public String getNoticeSummary() {
        return noticeSummary;
    }

    public void setNoticeSummary(String noticeSummary) {
        this.noticeSummary = noticeSummary;
    }

    public String getDisplayItem() {
        return displayItem;
    }

    public void setDisplayItem(String displayItem) {
        this.displayItem = displayItem;
    }

    public String getH5Url() {
        return h5Url;
    }

    public void setH5Url(String h5Url) {
        this.h5Url = h5Url;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getCircleType() {
        return circleType;
    }

    public void setCircleType(String circleType) {
        this.circleType = circleType;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
    }

    public ScheduleOpenUrlEntity getScheduleOpenUrl() {
        return scheduleOpenUrl;
    }

    public void setScheduleOpenUrl(ScheduleOpenUrlEntity scheduleOpenUrl) {
        this.scheduleOpenUrl = scheduleOpenUrl;
    }

    public String getScheduleDataUrl() {
        return scheduleDataUrl;
    }

    public void setScheduleDataUrl(String scheduleDataUrl) {
        this.scheduleDataUrl = scheduleDataUrl;
    }

    public String getUserDisplayItem() {
        return userDisplayItem;
    }

    public void setUserDisplayItem(String userDisplayItem) {
        this.userDisplayItem = userDisplayItem;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public List<?> getDataCircles() {
        return dataCircles;
    }

    public void setDataCircles(List<?> dataCircles) {
        this.dataCircles = dataCircles;
    }

    public static class ScheduleOpenUrlEntity {
        /**
         * name : 课表
         * installUrl :
         * openUrl : http://wectest2.wisedu.com/comapp/schoolTimetable/mobile/index.html
         */

        private String name;
        private String installUrl;
        private String openUrl;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInstallUrl() {
            return installUrl;
        }

        public void setInstallUrl(String installUrl) {
            this.installUrl = installUrl;
        }

        public String getOpenUrl() {
            return openUrl;
        }

        public void setOpenUrl(String openUrl) {
            this.openUrl = openUrl;
        }
    }
}
