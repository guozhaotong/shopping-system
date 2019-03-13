package com.guozhaotong.shoppingsystem.controller;

import com.guozhaotong.shoppingsystem.entity.*;
import com.guozhaotong.shoppingsystem.service.CommodityService;
import com.guozhaotong.shoppingsystem.service.OrderInfoService;
import com.guozhaotong.shoppingsystem.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 郭朝彤
 * @date 2019/3/3.
 */
@RestController
public class OrderInfoController {
    @Autowired
    OrderInfoService orderInfoService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    CommodityService commodityService;

    @GetMapping("/api/getOrderList")
    public ResultEntity getOrderList(long buyerId) {
        List<KV> res = new ArrayList<>();
        List<OrderInfo> orderInfoList = orderInfoService.getOrderListByBuyerId(buyerId);
        for (OrderInfo orderInfo : orderInfoList) {
            Commodity commodity = commodityService.getCommodity(orderInfo.getCommodityId());
            res.add(new KV(orderInfo, commodity));
        }
        return new ResultEntity(200, "success!", res);
    }

    @GetMapping("/api/sumPrice")
    public ResultEntity getSumPriceOfBuyer(long buyerId) {
        float res = orderInfoService.getSumPriceOfBuyer(buyerId);
        return new ResultEntity(200, "success!", res);
    }

    @PostMapping("/api/buyAllShoppingCart")
    public ResultEntity buy(long buyerId) {
        List<ShoppingCart> shoppingCartListOfBuyer = shoppingCartService.getShoppingCartList(buyerId);
        for (ShoppingCart shoppingCart : shoppingCartListOfBuyer) {
            if (commodityService.countCommodityById(shoppingCart.getCommodityId()) != 0) {
                orderInfoService.buyOne(buyerId, shoppingCart.getCommodityId(), shoppingCart.getNum());
            }
        }
        shoppingCartService.deleteShoppingCartByBuyerId(buyerId);
        return new ResultEntity(200, "success!", null);
    }
}
