package com.wisedu.cpdaily.utils;

import com.wisedu.cpdaily.Constants;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * cookie管理
 * Created by wjj on 2017/7/25 22:00.
 */

public class CookieJarManager implements CookieJar {
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookieList = new ArrayList<>();
        cookieList.add(new Cookie
                .Builder()
                .name("sessionToken")
                .value("fff8683b-356f-424f-a58e-b1de62244727")
                .hostOnlyDomain(Constants.URL.DOMAIN)
                .expiresAt(-1)
                .build());
        cookieList.add(new Cookie.Builder()
                .name("tenantId")
                .value("wisedu")
                .hostOnlyDomain(Constants.URL.DOMAIN)
                .expiresAt(-1)
                .build());
        return cookieList;
    }
}
