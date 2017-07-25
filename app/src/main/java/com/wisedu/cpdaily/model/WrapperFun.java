package com.wisedu.cpdaily.model;


import android.support.annotation.NonNull;

import com.wisedu.cpdaily.Constants;

import io.reactivex.functions.Function;

/**
 * http包裹map转换及预处理
 * Created by wjj on 2017/4/7 16:08.
 */

public class WrapperFun<T> implements Function<Wrapper<T>, T> {

    @Override
    public T apply(@NonNull Wrapper<T> wrapper) throws Exception {
        if (wrapper.getErrCode() == 0) {
            T data = wrapper.getData();
            if (data != null) {
                return data;
            }
            return (T) new Object();
        }
        if (wrapper.getErrCode() == -206) {
            throw new RuntimeException(Constants.RESPONSE.UNAUTHORIZED);
        } else if (wrapper.getErrCode() == 1202) {
            throw new RuntimeException(Constants.RESPONSE.RE_LOGIN);
        }
        throw new RuntimeException(Constants.RESPONSE.API_ERROR + wrapper.getErrMsg());
    }
}
