package com.guozhaotong.shoppingsystem.entity;

import java.util.Date;

/**
 * @author 郭朝彤
 * @date 2019/3/1.
 */
public class ShoppingCart {
    long id;
    long buyerId;
    long commodityId;
    int num;
    Date addTime;

    public ShoppingCart() {
    }

    public ShoppingCart(long buyerId, long commodityId, int num, Date addTime) {
        this.buyerId = buyerId;
        this.commodityId = commodityId;
        this.num = num;
        this.addTime = addTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(long commodityId) {
        this.commodityId = commodityId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", buyerId=" + buyerId +
                ", commodityId=" + commodityId +
                ", num=" + num +
                ", addTime=" + addTime +
                '}';
    }
}
