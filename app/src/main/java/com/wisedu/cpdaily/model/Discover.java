package com.wisedu.cpdaily.model;

import java.util.List;

/**
 * 发现
 * Created by wjj on 2017/7/21 13:50.
 */

public class Discover {
    public static final String FIND_CLASSMATE = "FIND_CLASSMATE";
    public static final String FIND_TEACHER = "FIND_TEACHER";
    public static final String FIND_MEDIA = "FIND_MEDIA";
    public static final String FIND_TRIBE = "FIND_TRIBE";
    /**
     * displayItems : FIND_CLASSMATE,FIND_TEACHER,FIND_MEDIA
     * isShowApp : true
     * circles : []
     */

    private String displayItems;
    private boolean isShowApp;
    private List<Circle> circles;

    public String getDisplayItems() {
        return displayItems;
    }

    public void setDisplayItems(String displayItems) {
        this.displayItems = displayItems;
    }

    public boolean isShowApp() {
        return isShowApp;
    }

    public void setShowApp(boolean showApp) {
        isShowApp = showApp;
    }

    public List<Circle> getCircles() {
        return circles;
    }

    public void setCircles(List<Circle> circles) {
        this.circles = circles;
    }
}
