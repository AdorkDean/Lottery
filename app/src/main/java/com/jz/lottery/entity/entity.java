package com.jz.lottery.entity;

/**
 * Created by cheng on 2017/7/25.
 */


import java.io.Serializable;

public class entity implements Serializable {
    private String description;
    private String id;
    private String litpic;
    private String pubdate;
    private String source;
    private String title;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLitpic() {
        return this.litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubdate() {
        return this.pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}