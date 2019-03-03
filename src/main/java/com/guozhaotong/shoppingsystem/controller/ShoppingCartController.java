package com.guozhaotong.shoppingsystem.controller;

import com.guozhaotong.shoppingsystem.entity.ShoppingCart;
import com.guozhaotong.shoppingsystem.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 郭朝彤
 * @date 2019/3/3.
 */
@RestController
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("/getShoppingCartList")
    public List<ShoppingCart> getShoppingCartList(long buyerId) {
        return shoppingCartService.getShoppingCartList(buyerId);
    }

    @PostMapping("/addCommodityToShoppingCart")
    public boolean addCommodityToShoppingCart(long buyerId, long commodityId, int num) {
        return shoppingCartService.addCommodityToShoppingCart(buyerId, commodityId, num);
    }

    @PostMapping("/deleteShoppingCartOneRecord")
    public boolean deleteShoppingCartOneRecord(long buyerId, long commodityId) {
        return shoppingCartService.deleteShoppingCartOneRecord(buyerId, commodityId);
    }

    public static void main(String[] args) {

    }
}
