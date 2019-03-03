package com.guozhaotong.shoppingsystem.controller;

import com.guozhaotong.shoppingsystem.entity.OrderInfo;
import com.guozhaotong.shoppingsystem.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author 郭朝彤
 * @date 2019/3/3.
 */
@RestController
public class OrderInfoController {
    @Autowired
    OrderInfoService orderInfoService;

    @GetMapping("/getOrderList")
    public List<OrderInfo> getOrderList(long buyerId) {
        return orderInfoService.getOrderList(buyerId);
    }

    @PostMapping("/buy")
    public boolean buy(long buyerId, long commodityId, Date time, int num, float price) {
        return orderInfoService.addNewOrder(new OrderInfo(buyerId, commodityId, time, num, price));
    }

    @GetMapping("/sumPrice")
    public float getSumPriceOfBuyer(long buyerId){
        return orderInfoService.getSumPriceOfBuyer(buyerId);
    }

    public static void main(String[] args) {

    }
}
