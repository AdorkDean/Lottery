package com.jz.lottery.utils;

/**
 * Created by cheng on 2017/7/25.
 */


import android.content.Context;

import org.xutils.http.RequestParams;

public class UrlAddParameterUtils {
    public static boolean isAddYear;
    private static RequestParams rp;
    private Context c;

    static {
        isAddYear = false;
    }

    public static RequestParams getRp() {
        return rp;
    }

    public static void setRp(RequestParams rp) {
        rp = rp;
    }

    public UrlAddParameterUtils(Context c) {
        this.c = c;
    }

    public static RequestParams getRequestParams(Context c, String url) {
        if (rp == null) {
            rp = new RequestParams(url);
        }
        return rp;
    }
}