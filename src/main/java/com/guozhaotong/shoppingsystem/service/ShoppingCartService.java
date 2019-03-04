package com.guozhaotong.shoppingsystem.service;

import com.guozhaotong.shoppingsystem.entity.ShoppingCart;
import com.guozhaotong.shoppingsystem.mapper.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 郭朝彤
 * @date 2019/3/3.
 */
@Service
public class ShoppingCartService {
    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    public List<ShoppingCart> getShoppingCartList(long buyerId) {
        return shoppingCartMapper.findByBuyerId(buyerId);
    }

    /**
     * 加入购物车分两种情况：1.用户购物车中已有该商品； 2.用户购物车中没有该商品
     * @param buyerId
     * @param commodityId
     * @param numOfAdd
     * @return
     */
    public boolean addCommodityToShoppingCart(long buyerId, long commodityId, int numOfAdd) {
        int numBefore = shoppingCartMapper.countBuyerIdAndCommodityId(buyerId, commodityId);
        int res;
        if (numBefore == 0) {
            ShoppingCart shoppingCart = new ShoppingCart(buyerId, commodityId, numOfAdd, new Date());
            res = shoppingCartMapper.insert(shoppingCart);
        } else {
            res = shoppingCartMapper.updateNumByBuyerIdAndCommodityId(numOfAdd + numBefore, buyerId, commodityId, new Date());
        }
        return res == 1;
    }

    public boolean deleteShoppingCartOneRecord(long buyerId, long commodityId){
        return shoppingCartMapper.deleteByBuyerIdAndCommodityId(buyerId, commodityId) == 1;
    }

    public float getSumPriceOfShoppingCart(long buyerId){
        return shoppingCartMapper.sumPrice(buyerId);
    }

    public int deleteShoppingCartByBuyerId(long buyerId){
        return shoppingCartMapper.deleteByBuyerId(buyerId);
    }
}
