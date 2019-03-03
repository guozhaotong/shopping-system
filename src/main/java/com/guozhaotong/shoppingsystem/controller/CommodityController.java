package com.guozhaotong.shoppingsystem.controller;

import com.guozhaotong.shoppingsystem.entity.Commodity;
import com.guozhaotong.shoppingsystem.service.CommodityService;
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
public class CommodityController {

    @Autowired
    CommodityService commodityService;

    @GetMapping("/getCommodityList")
    public List<Commodity> getCommodityList() {
        return commodityService.getCommodityList();
    }

    @PostMapping("/addNewCommodity")
    public boolean addNewCommodity(Commodity commodity) {
        return commodityService.addNewCommodity(commodity);
    }

    @PostMapping("/updateCommodity")
    public boolean updateCommodity(Commodity commodity) {
        return commodityService.updateCommodity(commodity);
    }

    @GetMapping("/getCommodity")
    public Commodity getCommodity(long commodityId) {
        return commodityService.getCommodity(commodityId);
    }

    public static void main(String[] args) {

    }
}
