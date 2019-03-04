package com.guozhaotong.shoppingsystem.service;

import com.guozhaotong.shoppingsystem.entity.OrderInfo;
import com.guozhaotong.shoppingsystem.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 郭朝彤
 * @date 2019/3/3.
 */
@Service
public class OrderInfoService {
    @Autowired
    OrderInfoMapper orderInfoMapper;

    public List<OrderInfo> getOrderListByBuyerId(long buyerId) {
        return orderInfoMapper.findByBuyerId(buyerId);
    }

    public List<OrderInfo> getOrderListBySellerId(long sellerId) {
        return orderInfoMapper.findBySellerId(sellerId);
    }

    /**
     * 用户已购买总价
     *
     * @param buyerId
     * @return
     */
    public float getSumPriceOfBuyer(long buyerId) {
        return orderInfoMapper.sumPrice(buyerId);
    }

    public boolean addNewOrder(OrderInfo orderInfo) {
        return orderInfoMapper.insert(orderInfo) == 1;
    }

    public static void main(String[] args) {

    }
}
