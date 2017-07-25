package com.wisedu.cpdaily.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 部门
 * Created by wjj on 2017/7/24 15:53.
 */

public class DepartVo implements Parcelable {

    public static final Creator<DepartVo> CREATOR = new Creator<DepartVo>() {
        @Override
        public DepartVo createFromParcel(Parcel in) {
            return new DepartVo(in);
        }

        @Override
        public DepartVo[] newArray(int size) {
            return new DepartVo[size];
        }
    };
    /**
     * id : wisedu02
     * name : 宣传部
     */

    private String id;
    private String name;

    protected DepartVo(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
