package com.guozhaotong.shoppingsystem.mapper;

import com.guozhaotong.shoppingsystem.entity.Commodity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 郭朝彤
 * @date 2019/3/1.
 */
@Component
public interface CommodityMapper {
    @Select("select * from commodity")
    List<Commodity> findAll();

    @Select("Select id from commodity")
    List<Long> findAllCommodityId();

    @Select("select * from commodity where id = #{id}")
    Commodity findById(@Param("id") long id);

    @Select("select count(*) from commodity")
    int countCommodity();

    @Select("select count(*) from commodity where id = #{id}")
    int countCommodityById(long id);

    @Delete("delete from commodity where id = #{id}")
    int deleteById(long id);

    @Insert("insert into commodity (seller_id, title, brief, intro, price, pic_addr) " +
            "values (#{sellerId}, #{title}, #{brief}, #{intro}, #{price}, #{picAddr})")
    int insert(Commodity commodity);

    @Update("update commodity set title = #{title}, brief = #{brief}, intro = #{intro}, price = #{price}, pic_addr = #{picAddr} " +
            "where id = #{id}")
    int updateNumByBuyerIdAndCommodityId(Commodity commodity);

    @Select("select pic_addr from commodity where id = #{id}")
    String getCommodityPicAddr(long id);

    @Select("select price from commodity where id = #{id}")
    float getCommodityPrice(long id);
}
