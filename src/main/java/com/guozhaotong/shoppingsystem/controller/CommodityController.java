package com.guozhaotong.shoppingsystem.controller;

import com.guozhaotong.shoppingsystem.entity.Commodity;
import com.guozhaotong.shoppingsystem.entity.OrderInfo;
import com.guozhaotong.shoppingsystem.entity.ResponseEntity;
import com.guozhaotong.shoppingsystem.entity.UserInfo;
import com.guozhaotong.shoppingsystem.service.CommodityService;
import com.guozhaotong.shoppingsystem.service.OrderInfoService;
import com.guozhaotong.shoppingsystem.service.UserInfoService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author 郭朝彤
 * @date 2019/3/3.
 */
@RestController
public class CommodityController {

    @Autowired
    CommodityService commodityService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    OrderInfoService orderInfoService;

    @GetMapping("/getCommodityList")
    public ResponseEntity getCommodityList(String userName) {
        LinkedHashMap<Commodity, Integer> res = new LinkedHashMap<>();
        UserInfo userInfo = userInfoService.findByUserName(userName);
        String userIdentity = userInfo.getIdentity();
        List<Commodity> commodityList = commodityService.getCommodityList();
        //无用户登录时，展示物品列表
        if (userName == null) {
            for (Commodity commodity : commodityList) {
                res.put(commodity, -1);
            }
        }
        //买家登录时，展示物品列表并标明已购买
        else if ("buyer".equals(userIdentity)) {
            List<OrderInfo> orderInfoList = orderInfoService.getOrderListByBuyerId(userInfo.getId());
            for (Commodity commodity : commodityList) {
                res.put(commodity, 0);
            }
            for (OrderInfo orderInfo : orderInfoList) {
                res.put(commodityService.getCommodity(orderInfo.getCommodityId()), 1);
            }
        }
        //卖家登录时，展示物品列表并标明卖出数量
        else {
            List<OrderInfo> orderInfoList = orderInfoService.getOrderListBySellerId(userInfo.getId());
            for (Commodity commodity : commodityList) {
                res.put(commodity, 0);
            }
            for (OrderInfo orderInfo : orderInfoList) {
                res.put(commodityService.getCommodity(orderInfo.getCommodityId()), res.get(commodityService.getCommodity(orderInfo.getCommodityId())) + orderInfo.getNum());
            }
        }
        return new ResponseEntity(200, "success!", res);
    }

    @PostMapping("/addNewCommodity")
    public ResponseEntity addNewCommodity(Commodity commodity) {
        boolean res = commodityService.addNewCommodity(commodity);
        return new ResponseEntity(200, "success!", res);
    }

    @PostMapping("/updateCommodity")
    public ResponseEntity updateCommodity(Commodity commodity) {
        boolean res = commodityService.updateCommodity(commodity);
        commodityService.deleteUselessPic(commodity.getId());
        return new ResponseEntity(200, "success!", res);
    }

    @GetMapping("/getCommodity")
    public ResponseEntity getCommodity(long commodityId) {
        Commodity res = commodityService.getCommodity(commodityId);
        return new ResponseEntity(200, "success!", res);
    }

    @PostMapping("/updatePic")
    public ResponseEntity uploadFile(MultipartFile file, long commodityId) {
        String filename = file.getOriginalFilename();
        String realPath = System.getProperty("user.home") + "/shopping_system_img/";
        File fileDir = new File(realPath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        String extName = FilenameUtils.getExtension(filename);
        String allowImgFormat = "gif,jpg,jpeg,png";
        if (!allowImgFormat.contains(extName.toLowerCase())) {
            return new ResponseEntity(400, "No image!", "NOT_IMAGE");
        }

        filename = commodityId + "_" + System.currentTimeMillis() + "." + extName;
        InputStream input = null;
        FileOutputStream fos = null;
        try {
            input = file.getInputStream();
            fos = new FileOutputStream(realPath + "/" + filename);
            IOUtils.copy(input, fos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(fos);
        }
        return new ResponseEntity(200, "Success!", filename);
    }

    @GetMapping(value = "/showPic")
    public org.springframework.http.ResponseEntity showPic(String fileName) {
        org.springframework.http.ResponseEntity responseEntity = null;
        String filePath = System.getProperty("user.home") + "/shopping_system_img/";
        File file = new File(filePath + fileName);
        if (file.exists()) { //判断文件父目录是否存在
            Object att = null;
            try {
                att = FileUtils.readFileToByteArray(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                responseEntity = org.springframework.http.ResponseEntity
                        .status(HttpStatus.OK)
                        .header("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName.substring(23), "UTF-8"))
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(att);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return responseEntity;

    }
}
