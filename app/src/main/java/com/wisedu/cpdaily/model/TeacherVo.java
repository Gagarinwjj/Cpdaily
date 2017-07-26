package com.wisedu.cpdaily.model;


import android.text.TextUtils;

/**
 * 老师对象
 * Created by wjj on 2017/7/24 16:27.
 */

public class TeacherVo {


    /**
     * id : 07504352-e956-4b45-a8b6-9168c5d63f73
     * name : 杨瑞妮
     * alias : 瑞妮老师
     * imgUrl : http://img.cpdaily.com/wisedu/image/1499925197867.jpg
     * job :
     * mobilePhone : 20170171
     * telePhone :
     * gender : MALE
     * userRole : TEACHER
     */

    private String id;
    private String name;
    private String alias;
    private String imgUrl;
    private String job;
    private String mobilePhone;
    private String telePhone;
    private String gender;
    private String userRole;

    public String getSmallImg() {//内部封装，简化外部调用
        if (!TextUtils.isEmpty(imgUrl) && imgUrl.endsWith(".jpg"))
            return imgUrl + "!small";
        return imgUrl;
    }

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
