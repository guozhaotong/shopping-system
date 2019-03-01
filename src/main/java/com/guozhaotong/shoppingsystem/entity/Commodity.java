package com.guozhaotong.shoppingsystem.entity;

/**
 * @author 郭朝彤
 * @date 2019/3/1.
 */
public class Commodity {
    long id;
    long sellerId;
    String title;
    String brief;
    String intro;
    float price;
    String picAddr;

    public Commodity() {
    }

    public Commodity(long sellerId, String title, String brief, String intro, float price, String picAddr) {
        this.sellerId = sellerId;
        this.title = title;
        this.brief = brief;
        this.intro = intro;
        this.price = price;
        this.picAddr = picAddr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPicAddr() {
        return picAddr;
    }

    public void setPicAddr(String picAddr) {
        this.picAddr = picAddr;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", intro='" + intro + '\'' +
                ", price=" + price +
                ", picAddr='" + picAddr + '\'' +
                '}';
    }
}
