package com.jz.lottery.entity;

/**
 * Created by cheng on 2017/7/25.
 */


public class ZixunDetialEntity {
    private String body;
    private String pubdate;
    private String source;
    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPubdate() {
        return this.pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}