package com.jz.lottery;

/**
 * Created by cheng on 2017/7/25.
 */


import android.app.Application;

import org.xutils.x;

import java.util.HashSet;
import java.util.Set;

import cn.bmob.v3.Bmob;
import cn.jpush.android.api.JPushInterface;


public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "6e0cc180d7ea5dd087e414569e96eb23");
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        Set<String> set = new HashSet();
        set.add("andfixdemo");
        JPushInterface.setTags(this, set, null);
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
