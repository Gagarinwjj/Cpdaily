package com.wisedu.cpdaily.model;


import android.text.TextUtils;

import java.util.List;

/**
 * 用户完整信息
 * http://confluence.next.wisedu.com:8090/pages/viewpage.action?pageId=3834206
 * Created by wjj on 2017/7/21 15:25.
 */

public class UserComplete {
    public static final String GENDER_MALE = "MALE";
    public static final String GENDER_FEMALE = "FEMALE";
    public static final String USERROLE_STUDENT = "STUDENT";
    public static final String USERROLE_TEACHER = "TEACHER";
    public static final String USERROLE_MEDIA = "MEDIA";

    /**
     * id : f6eb8844-331e-4e95-bb91-96bd73a3009d
     * name : 于海军
     * alias : 于海军12军副参谋长联席会议上
     * gender : MALE
     * img : http://img.cpdaily.com/wisedu/image/1500011417936.jpg
     * backgroundImg : http://img.cpdaily.com/wisedu/image/1500011417936.jpg
     * userSrcType : IDS
     * userRole : STUDENT
     * tenant : 金智大学
     * tenantShortName : 金智大学
     * grade : 2017
     * studentNo : 20170100
     * isFocused : true
     * signature : Key I d
     * depart :
     * departId : wisedu08
     * departShortName :
     * job :
     * academy : 音乐学院
     * academyShortName :
     * major : 资源环境与城乡规划管理(城乡规划与景观设计)
     * mobilePhone :
     * telePhone :
     * email :
     * officeAddr :
     * orgFullName :
     * collegeId :
     * collegeName :
     * menus : []
     * fansCount : 0
     * isGetNewbieTaskBadge : false
     */

    private String id;
    private String name;
    private String alias;
    private String gender;
    private String img;
    private String backgroundImg;
    private String userSrcType;
    private String userRole;
    private String tenant;
    private String tenantShortName;
    private String grade;
    private String studentNo;
    private boolean isFocused;
    private String signature;
    private String depart;
    private String departId;
    private String departShortName;
    private String job;
    private String academy;
    private String academyShortName;
    private String major;
    private String mobilePhone;
    private String telePhone;
    private String email;
    private String officeAddr;
    private String orgFullName;
    private String collegeId;
    private String collegeName;
    private int fansCount;
    private boolean isGetNewbieTaskBadge;
    private List<?> menus;

    public String getSmallImg() {//内部封装，简化外部调用
        if (!TextUtils.isEmpty(img) && img.endsWith(".jpg"))
            return img + "!small";
        return img;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public String getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
    }

    public String getUserSrcType() {
        return userSrcType;
    }

    public void setUserSrcType(String userSrcType) {
        this.userSrcType = userSrcType;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getTenantShortName() {
        return tenantShortName;
    }

    public void setTenantShortName(String tenantShortName) {
        this.tenantShortName = tenantShortName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public boolean isIsFocused() {
        return isFocused;
    }

    public void setIsFocused(boolean isFocused) {
        this.isFocused = isFocused;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getDepartShortName() {
        return departShortName;
    }

    public void setDepartShortName(String departShortName) {
        this.departShortName = departShortName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getAcademyShortName() {
        return academyShortName;
    }

    public void setAcademyShortName(String academyShortName) {
        this.academyShortName = academyShortName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficeAddr() {
        return officeAddr;
    }

    public void setOfficeAddr(String officeAddr) {
        this.officeAddr = officeAddr;
    }

    public String getOrgFullName() {
        return orgFullName;
    }

    public void setOrgFullName(String orgFullName) {
        this.orgFullName = orgFullName;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public boolean isIsGetNewbieTaskBadge() {
        return isGetNewbieTaskBadge;
    }

    public void setIsGetNewbieTaskBadge(boolean isGetNewbieTaskBadge) {
        this.isGetNewbieTaskBadge = isGetNewbieTaskBadge;
    }

    public List<?> getMenus() {
        return menus;
    }

    public void setMenus(List<?> menus) {
        this.menus = menus;
    }
}
