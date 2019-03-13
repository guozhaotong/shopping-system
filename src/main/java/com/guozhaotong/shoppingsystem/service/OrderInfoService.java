package com.guozhaotong.shoppingsystem.service;

import com.guozhaotong.shoppingsystem.entity.OrderInfo;
import com.guozhaotong.shoppingsystem.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author 郭朝彤
 * @date 2019/3/3.
 */
@Service
public class OrderInfoService {
    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    CommodityService commodityService;

    @Autowired
    ShoppingCartService shoppingCartService;


    public List<OrderInfo> getOrderListByBuyerId(long buyerId) {
        return orderInfoMapper.findByBuyerId(buyerId);
    }


    /**
     * 用户已购买总价
     *
     * @param buyerId
     * @return
     */
    public float getSumPriceOfBuyer(long buyerId) {
        Optional<Float> sumPrice = orderInfoMapper.sumPrice(buyerId);
        return sumPrice.orElse(0F);
    }

    public void buyOne(long buyerId, long commodityId, int num) {
        boolean res = addNewOrder(new OrderInfo(buyerId, commodityId, new Date(), num, commodityService.getCommodityPrice(commodityId)));
        //购买后，购物车中的内容随之删除
        shoppingCartService.deleteShoppingCartOneRecord(buyerId, commodityId);
    }

    public boolean addNewOrder(OrderInfo orderInfo) {
        return orderInfoMapper.insert(orderInfo) == 1;
    }

    public OrderInfo findByCommodityId(long commodityId) {
        return orderInfoMapper.findByCommodityId(commodityId);
    }

    public int countByCommodityIdAndBuyerId(long commodityId, long buyerId) {
        return orderInfoMapper.countByCommodityIdAndBuyerId(commodityId, buyerId).orElse(0);
    }

    public int countByCommodity(long commodityId) {
        return orderInfoMapper.countByCommodityId(commodityId).orElse(0);
    }

}
