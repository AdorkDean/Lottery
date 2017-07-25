package com.jz.lottery.entity;

import cn.bmob.v3.BmobObject;

/**
 * Created by cheng on 2017/7/25.
 */



public class Config extends BmobObject {
    private String appid;
    private boolean show;
    private String url;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isShow() {
        return this.show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getAppid() {
        return this.appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String toString() {
        return "Config{url='" + this.url + '\'' + ", show=" + this.show + ", appid='" + this.appid + '\'' + '}';
    }
}