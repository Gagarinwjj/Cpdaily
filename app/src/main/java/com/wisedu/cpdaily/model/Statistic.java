package com.wisedu.cpdaily.model;

/**
 * 关注数、粉丝数
 * Created by wjj on 2017/7/23 16:59.
 */

public class Statistic {

    /**
     * fans : 6
     * dynamic : 1
     * focus : 14
     * lastActTime : 1分钟前
     */

    private int fans;
    private int dynamic;
    private int focus;
    private String lastActTime;

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getDynamic() {
        return dynamic;
    }

    public void setDynamic(int dynamic) {
        this.dynamic = dynamic;
    }

    public int getFocus() {
        return focus;
    }

    public void setFocus(int focus) {
        this.focus = focus;
    }

    public String getLastActTime() {
        return lastActTime;
    }

    public void setLastActTime(String lastActTime) {
        this.lastActTime = lastActTime;
    }
}
