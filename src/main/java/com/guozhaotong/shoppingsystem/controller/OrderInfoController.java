package com.guozhaotong.shoppingsystem.controller;

import com.guozhaotong.shoppingsystem.entity.OrderInfo;
import com.guozhaotong.shoppingsystem.entity.ResultEntity;
import com.guozhaotong.shoppingsystem.entity.ShoppingCart;
import com.guozhaotong.shoppingsystem.service.CommodityService;
import com.guozhaotong.shoppingsystem.service.OrderInfoService;
import com.guozhaotong.shoppingsystem.service.ShoppingCartService;
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

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    CommodityService commodityService;

    @GetMapping("/api/getOrderList")
    public ResultEntity getOrderList(long buyerId) {
        List<OrderInfo> res = orderInfoService.getOrderListByBuyerId(buyerId);
        return new ResultEntity(200, "success!", res);
    }

//    @PostMapping("/api/buyOne")
    public ResultEntity buyOne(long buyerId, long commodityId, int num) {
        boolean res =  orderInfoService.addNewOrder(new OrderInfo(buyerId, commodityId, new Date(), num, commodityService.getCommodityPrice(commodityId)));
        //购买后，购物车中的内容随之删除
        shoppingCartService.deleteShoppingCartOneRecord(buyerId, commodityId);
        return new ResultEntity(200, "success!", res);
    }

    @GetMapping("/api/sumPrice")
    public ResultEntity getSumPriceOfBuyer(long buyerId){
        float res = orderInfoService.getSumPriceOfBuyer(buyerId);
        return new ResultEntity(200, "success!", res);
    }

    @PostMapping("/api/buyAllShoppingCart")
    public ResultEntity buy(long buyerId){
        List<ShoppingCart> shoppingCartListOfBuyer = shoppingCartService.getShoppingCartList(buyerId);
        for(ShoppingCart shoppingCart : shoppingCartListOfBuyer){
            if(commodityService.countCommodityById(shoppingCart.getCommodityId()) != 0) {
                buyOne(buyerId, shoppingCart.getCommodityId(), shoppingCart.getNum());
            }
        }
        shoppingCartService.deleteShoppingCartByBuyerId(buyerId);
        return new ResultEntity(200, "success!", null);
    }
}
