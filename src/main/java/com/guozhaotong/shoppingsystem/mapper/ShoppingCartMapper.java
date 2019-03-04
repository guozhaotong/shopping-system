package com.guozhaotong.shoppingsystem.mapper;

import com.guozhaotong.shoppingsystem.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author 郭朝彤
 * @date 2019/3/1.
 */
public interface ShoppingCartMapper {
    @Select("select * from shopping_cart where buyer_id = #{buyer_id}")
    List<ShoppingCart> findByBuyerId(@Param("buyer_id") long buyerId);

    @Delete("delete from shopping_cart where buyer_id = #{buyerId} and commodity_id= #{commodityId}")
    int deleteByBuyerIdAndCommodityId(long buyerId, long commodityId);

    @Delete("delete from shopping_cart where buyer_id = #{buyerId}")
    int deleteByBuyerId(long buyerId);

    @Insert("insert into shopping_cart (buyer_id, commodity_id, num, add_time) values (#{buyerId}, #{commodityId}, " +
            "#{num}, #{addTime})") //#{addTime, jdbcType=TIMESTAMP}
    int insert(ShoppingCart shoppingCart);

    @Select("select count(*) from shopping_cart where buyer_id = #{buyerId} and commodity_id= #{commodityId}")
    int countBuyerIdAndCommodityId(long buyerId, long commodityId);

    @Update("update shopping_cart set num = #{num}, add_time = #{add_time} where buyer_id = #{buyerId} and commodity_id= #{commodityId}")
    int updateNumByBuyerIdAndCommodityId(int num, long buyerId, long commodityId, @Param("add_time") Date addTime);

    @Select("select sum(s.num * c.price ) from commodity c, shopping_cart s where c.id = s.commodity_id and s.buyer_id = #{buyer_id}")
    float sumPrice(@Param("buyer_id") long buyerId);
}
