package com.guozhaotong.shoppingsystem.controller;

import com.guozhaotong.shoppingsystem.entity.Commodity;
import com.guozhaotong.shoppingsystem.entity.ResponseEntity;
import com.guozhaotong.shoppingsystem.entity.ShoppingCart;
import com.guozhaotong.shoppingsystem.service.CommodityService;
import com.guozhaotong.shoppingsystem.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
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

    @GetMapping("/getShoppingCartList")
    public ResponseEntity getShoppingCartList(long buyerId) {
        LinkedHashMap<ShoppingCart, Boolean> res = new LinkedHashMap<>();
        List<ShoppingCart> shoppingCartList = shoppingCartService.getShoppingCartList(buyerId);
        List<Long> commodityIdList = commodityService.getCommodityIdList();
        for (ShoppingCart shoppingCart : shoppingCartList) {
            if (commodityIdList.contains(shoppingCart.getCommodityId())) {
                res.put(shoppingCart, true);
            } else {
                res.put(shoppingCart, false);
            }
        }

        return new ResponseEntity(200, "success!", res);
    }

    @PostMapping("/addCommodityToShoppingCart")
    public ResponseEntity addCommodityToShoppingCart(long buyerId, long commodityId, int num) {
        boolean res = shoppingCartService.addCommodityToShoppingCart(buyerId, commodityId, num);
        return new ResponseEntity(200, "success!", res);
    }

    @PostMapping("/deleteShoppingCartOneRecord")
    public ResponseEntity deleteShoppingCartOneRecord(long buyerId, long commodityId) {
        boolean res = shoppingCartService.deleteShoppingCartOneRecord(buyerId, commodityId);
        return new ResponseEntity(200, "success!", res);
    }

    @GetMapping("/getSumPriceOfShoppingCart")
    public ResponseEntity getSumPriceOfShoppingCart(long buyerId) {
        float res = shoppingCartService.getSumPriceOfShoppingCart(buyerId);
        return new ResponseEntity(200, "success!", res);
    }
}
