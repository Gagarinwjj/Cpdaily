package com.wisedu.cpdaily.model;

/**
 * 实体类包裹
 * Created by wjj on 2017/4/7 14:47.
 */

public class Wrapper<T> {
    int errCode;
    String errMsg;
    T data;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
