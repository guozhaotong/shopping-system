package com.guozhaotong.shoppingsystem.runner;

import com.guozhaotong.shoppingsystem.mapper.CommodityMapper;
import com.guozhaotong.shoppingsystem.mapper.OrderInfoMapper;
import com.guozhaotong.shoppingsystem.mapper.ShoppingCartMapper;
import com.guozhaotong.shoppingsystem.mapper.UserInfoMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;

/**
 * @author 郭朝彤
 * @date 2019/3/1.
 */
@Component
public class CopyDemoPictureRunner implements CommandLineRunner {


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
        //将样例照片放到指定目录
        String path = System.getProperty("user.home") + "/shopping_system_img/";
        for (int i = 1; i <= 6; i++) {
            File file = ResourceUtils.getFile("classpath:static/img/" + i + ".jpg");
            FileUtils.copyFile(file, new File(path + i + ".jpg"));
        }
    }
}
