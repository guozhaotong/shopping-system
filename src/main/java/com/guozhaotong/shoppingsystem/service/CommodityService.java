package com.guozhaotong.shoppingsystem.service;

import com.guozhaotong.shoppingsystem.entity.Commodity;
import com.guozhaotong.shoppingsystem.mapper.CommodityMapper;
import com.guozhaotong.shoppingsystem.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author 郭朝彤
 * @date 2019/3/3.
 */
@Service
public class CommodityService {
    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    OrderInfoMapper orderInfoMapper;

    public List<Commodity> getCommodityList() {
        return commodityMapper.findAll();
    }

    public List<Long> getCommodityIdList(){
        return commodityMapper.findAllCommodityId();
    }

    public boolean addNewCommodity(Commodity commodity) {
        if (commodityMapper.countCommodity() < 1000) {
            commodityMapper.insert(commodity);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateCommodity(Commodity commodity) {
        return commodityMapper.updateNumByBuyerIdAndCommodityId(commodity) == 1;
    }

    public Commodity getCommodity(long commodityId) {
        return commodityMapper.findByCommodityId(commodityId);
    }

    /**
     * 题目要求卖出去过的商品不能被删除。
     *
     * @param commodityId
     * @return
     */
    public boolean delete(long commodityId) {
        int sellNum = orderInfoMapper.countByCommodityId(commodityId);
        if (sellNum == 0) {
            commodityMapper.deleteById(commodityId);
            return true;
        } else {
            return false;
        }
    }

    public void deleteUselessPic(long commodityId) {
        String picAddr = commodityMapper.getCommodityPicAddr(commodityId);
        String realPath = System.getProperty("user.home") + "\\shopping_system_img\\";
        File fileDir = new File(realPath);
        String[] fileNames = fileDir.list();
        if(fileNames != null){
            for (String fileName : fileNames) {
                if (fileName.startsWith(commodityId + "_") && !fileName.equals(picAddr)) {
                    File fileDelete = new File(fileName);
                    fileDelete.delete();
                    break;
                }
            }
        }
    }

    public float getCommodityPrice(long id){
        return commodityMapper.getCommodityPrice(id);
    }

    public int countCommodityById(long id){
        return commodityMapper.countCommodityById(id);
    }
}
