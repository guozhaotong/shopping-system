package com.guozhaotong.shoppingsystem.controller;

import com.guozhaotong.shoppingsystem.entity.*;
import com.guozhaotong.shoppingsystem.service.CommodityService;
import com.guozhaotong.shoppingsystem.service.OrderInfoService;
import com.guozhaotong.shoppingsystem.service.UserInfoService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
    public ResultEntity getCommodityList(String userName) {
        List<KV> res = new ArrayList<>();

        List<Commodity> commodityList = commodityService.getCommodityList();
        //无用户登录时，展示物品列表
        if (userName == null) {
            for (Commodity commodity : commodityList) {
                res.add(new KV(commodity, -1));
            }
            return new ResultEntity(200, "success!", res);
        }
        UserInfo userInfo = userInfoService.findByUserName(userName);
        String userIdentity = userInfo.getIdentity();
        //买家登录时，展示物品列表并标明已购买
        if ("buyer".equals(userIdentity)) {
            List<OrderInfo> orderInfoList = orderInfoService.getOrderListByBuyerId(userInfo.getId());
            for (Commodity commodity : commodityList) {
                res.add(new KV(commodity, orderInfoService.countByCommodityIdAndBuyerId(commodity.getId(), userInfo.getId())));
            }
        }
        //卖家登录时，展示物品列表并标明卖出数量
        else {
            for (Commodity commodity : commodityList) {
                res.add(new KV(commodity, orderInfoService.countByCommodity(commodity.getId())));
            }
        }
        return new ResultEntity(200, "success!", res);
    }

    @PostMapping("/api/addNewCommodity")
    public ResultEntity addNewCommodity(Commodity commodity) {
        boolean res = commodityService.addNewCommodity(commodity);
        return new ResultEntity(200, "success!", res);
    }

    @PostMapping("/api/updateCommodity")
    public ResultEntity updateCommodity(Commodity commodity) {
        commodityService.deleteUselessPic(commodity.getId(), commodity.getPicAddr());
        boolean res = commodityService.updateCommodity(commodity);
        return new ResultEntity(200, "success!", res);
    }

    @PostMapping("/api/deleteCommodity")
    public ResultEntity deleteCommodity(Commodity commodity) {
        boolean res = commodityService.delete(commodity.getId(), commodity.getPicAddr());
        return new ResultEntity(200, "success!", res);
    }

    @GetMapping("/getCommodity")
    public ResultEntity getCommodity(long commodityId) {
        Commodity commodity = commodityService.getCommodity(commodityId);
        OrderInfo orderInfo = orderInfoService.findByCommodityId(commodityId);
        HashMap<String, Object> res = new HashMap<>();
        res.put("commodity", commodity);
        res.put("orderInfo", orderInfo);
        return new ResultEntity(200, "success!", res);
    }

    private String getPicPath() {
        return System.getProperty("user.home") + "/shopping_system_img/";
    }

    @PostMapping("/api/updatePic")
    public ResultEntity uploadFile(MultipartFile imgFile) {
        String filename = imgFile.getOriginalFilename();
        String realPath = getPicPath();
        File fileDir = new File(realPath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        String extName = FilenameUtils.getExtension(filename);
        String allowImgFormat = "gif,jpg,jpeg,png";
        if (!allowImgFormat.contains(extName.toLowerCase())) {
            return new ResultEntity(400, "No image!", "NOT_IMAGE");
        }

        filename = UUID.randomUUID().toString() + "." + extName;
        InputStream input = null;
        FileOutputStream fos = null;
        try {
            input = imgFile.getInputStream();
            fos = new FileOutputStream(realPath + "/" + filename);
            IOUtils.copy(input, fos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(fos);
        }
        return new ResultEntity(200, "Success!", filename);
    }

    @GetMapping(value = "/showPic")
    public org.springframework.http.ResponseEntity showPic(String fileName) throws FileNotFoundException {
        org.springframework.http.ResponseEntity responseEntity = null;
        String filePath = getPicPath();
        File file = null;
        if (fileName == null || fileName.trim().equals("") || fileName.trim().equals("null")) {
            file = ResourceUtils.getFile("classpath:static/img/default.png");
            fileName = "default.png";
        } else {
            file = new File(filePath + fileName);
        }
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
                        .header("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"))
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(att);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return responseEntity;

    }
}
