package com.wisedu.cpdaily.model;

/**
 * 通讯录动态菜单
 * Created by wjj on 2017/7/21 15:40.
 */

public class DisplayItem {
    private String name;
    private String flag;
    private int imgResId;

    public DisplayItem(String name, String flag, int imgResId) {
        this.name = name;
        this.flag = flag;
        this.imgResId = imgResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }
}
