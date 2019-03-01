package com.guozhaotong.shoppingsystem.entity;

import java.util.Date;

/**
 * @author 郭朝彤
 * @date 2019/3/1.
 */
public class OrderInfo {
    long id;
    long buyerId;
    long commodityId;
    Date finishTime;
    int num;
    float priceWhenBuy;

    public OrderInfo() {
    }

    public OrderInfo(long buyerId, long commodityId, Date finishTime, int num, float priceWhenBuy) {
        this.buyerId = buyerId;
        this.commodityId = commodityId;
        this.finishTime = finishTime;
        this.num = num;
        this.priceWhenBuy = priceWhenBuy;
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

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getPriceWhenBuy() {
        return priceWhenBuy;
    }

    public void setPriceWhenBuy(float priceWhenBuy) {
        this.priceWhenBuy = priceWhenBuy;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", buyerId=" + buyerId +
                ", commodityId=" + commodityId +
                ", finishTime=" + finishTime +
                ", num=" + num +
                ", priceWhenBuy=" + priceWhenBuy +
                '}';
    }
}
