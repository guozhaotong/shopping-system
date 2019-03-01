package com.guozhaotong.shoppingsystem.mapper;

import com.guozhaotong.shoppingsystem.entity.Commodity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author 郭朝彤
 * @date 2019/3/1.
 */
public interface CommodityMapper {
    @Select("select * from commodity")
    List<Commodity> findAll();

    @Delete("delete from commodity where id = #{id}")
    int deleteById(long id);

    @Insert("insert into commodity (seller_id, title, brief, intro, price, pic_addr) " +
            "values (#{sellerId}, #{title}, #{brief}, #{intro}, #{price}, #{picAddr})")
    int insert(Commodity commodity);

    @Update("update commodity set title = #{title}, brief = #{brief}, intro = #{intro}, price = #{price}, pic_addr = #{picAddr} " +
            "where id = #{id}")
    int updateNumByBuyerIdAndCommodityId(Commodity commodity);
}
