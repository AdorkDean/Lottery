package com.jz.lottery.entity;

/**
 * Created by cheng on 2017/7/26.
 */

public class LotteryEntity {
    private String haoma;
    private int img;
    private String name;
    private String qishu;
    private String time;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LotteryEntity(int img, String name, String qishu, String haoma, String time) {
        this.img = img;
        this.name = name;
        this.qishu = qishu;
        this.haoma = haoma;
        this.time = time;
    }

    public int getImg() {
        return this.img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getQishu() {
        return this.qishu;
    }

    public void setQishu(String qishu) {
        this.qishu = qishu;
    }

    public String getHaoma() {
        return this.haoma;
    }

    public void setHaoma(String haoma) {
        this.haoma = haoma;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
