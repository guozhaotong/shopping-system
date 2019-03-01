package com.guozhaotong.shoppingsystem.runner;

import com.guozhaotong.shoppingsystem.entity.Commodity;
import com.guozhaotong.shoppingsystem.mapper.CommodityMapper;
import com.guozhaotong.shoppingsystem.mapper.OrderInfoMapper;
import com.guozhaotong.shoppingsystem.mapper.ShoppingCartMapper;
import com.guozhaotong.shoppingsystem.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author 郭朝彤
 * @date 2019/3/1.
 */
@Component
public class Runner implements CommandLineRunner {


    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(userInfoMapper.findByName("seller"));
        System.out.println(commodityMapper.insert(new Commodity(2, "testTitle", "testBrief", "testIntro", 1.01f, "testAddr")));


    }
}
