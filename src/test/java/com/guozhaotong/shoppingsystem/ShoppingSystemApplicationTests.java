package com.guozhaotong.shoppingsystem;

import com.guozhaotong.shoppingsystem.entity.Commodity;
import com.guozhaotong.shoppingsystem.mapper.CommodityMapper;
import com.guozhaotong.shoppingsystem.mapper.OrderInfoMapper;
import com.guozhaotong.shoppingsystem.mapper.ShoppingCartMapper;
import com.guozhaotong.shoppingsystem.mapper.UserInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingSystemApplicationTests {



	@Autowired
	UserInfoMapper userInfoMapper;

	@Autowired
	CommodityMapper commodityMapper;

	@Autowired
	ShoppingCartMapper shoppingCartMapper;

	@Autowired
	OrderInfoMapper orderInfoMapper;

	@Test
	public void t1() {
		System.out.println(userInfoMapper.findByName("seller"));
        System.out.println(commodityMapper.insert(new Commodity(2, "testTitle", "testBrief", "testIntro", 1.01f, "testAddr")));

	}

}
