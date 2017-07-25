package com.wisedu.cpdaily.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义@Scope
 * Created by wjj on 2017/7/3 17:33.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface ScopeFragment {
}
