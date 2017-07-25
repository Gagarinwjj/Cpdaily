package com.wisedu.cpdaily.model;

import java.util.ArrayList;

/**
 * 学院及专业
 * Created by wjj on 2017/7/24 14:06.
 */

public class Academy {
    /**
     * id : wisedu11
     * name : 计算机学院
     * majors : [{"id":"wisedu1101","name":"计算机科学与技术"}]
     */

    private String id;
    private String name;
    private ArrayList<PickItem> majors;

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

    public ArrayList<PickItem> getMajors() {
        return majors;
    }

    public void setMajors(ArrayList<PickItem> majors) {
        this.majors = majors;
    }

}
