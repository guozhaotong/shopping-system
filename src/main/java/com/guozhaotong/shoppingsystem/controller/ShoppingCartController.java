package com.guozhaotong.shoppingsystem.controller;

import com.guozhaotong.shoppingsystem.entity.KV;
import com.guozhaotong.shoppingsystem.entity.ResultEntity;
import com.guozhaotong.shoppingsystem.entity.ShoppingCart;
import com.guozhaotong.shoppingsystem.service.CommodityService;
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
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    CommodityService commodityService;

    @GetMapping("/api/getShoppingCartList")
    public ResultEntity getShoppingCartList(long buyerId) {
        List<KV> res = new ArrayList<>();
//        LinkedHashMap<ShoppingCart, Boolean> res = new LinkedHashMap<>();
        List<ShoppingCart> shoppingCartList = shoppingCartService.getShoppingCartList(buyerId);
        List<Long> commodityIdList = commodityService.getCommodityIdList();
        for (ShoppingCart shoppingCart : shoppingCartList) {
            if (commodityIdList.contains(shoppingCart.getCommodityId())) {
                res.add(new KV(shoppingCart, true));
            } else {
                res.add(new KV(shoppingCart, false));
            }
        }

        return new ResultEntity(200, "success!", res);
    }

    @PostMapping("/api/addCommodityToShoppingCart")
    public ResultEntity addCommodityToShoppingCart(long buyerId, long commodityId, int num) {
        boolean res = shoppingCartService.addCommodityToShoppingCart(buyerId, commodityId, num);
        return new ResultEntity(200, "success!", res);
    }

    @PostMapping("/api/deleteShoppingCartOneRecord")
    public ResultEntity deleteShoppingCartOneRecord(long buyerId, long commodityId) {
        boolean res = shoppingCartService.deleteShoppingCartOneRecord(buyerId, commodityId);
        return new ResultEntity(200, "success!", res);
    }

    @GetMapping("/api/getSumPriceOfShoppingCart")
    public ResultEntity getSumPriceOfShoppingCart(long buyerId) {
        float res = shoppingCartService.getSumPriceOfShoppingCart(buyerId);
        return new ResultEntity(200, "success!", res);
    }
}
