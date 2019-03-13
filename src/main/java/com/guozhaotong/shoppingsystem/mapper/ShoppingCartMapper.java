package com.guozhaotong.shoppingsystem.mapper;

import com.guozhaotong.shoppingsystem.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author 郭朝彤
 * @date 2019/3/1.
 */
@Component
public interface ShoppingCartMapper {
    @Select("select * from shopping_cart where buyer_id = #{buyer_id} order by add_time desc")
    List<ShoppingCart> findByBuyerId(@Param("buyer_id") long buyerId);

    @Delete("delete from shopping_cart where buyer_id = #{buyerId} and commodity_id= #{commodityId}")
    int deleteByBuyerIdAndCommodityId(@Param("buyerId") long buyerId, @Param("commodityId") long commodityId);

    @Delete("delete from shopping_cart where buyer_id = #{buyerId}")
    int deleteByBuyerId(@Param("buyerId") long buyerId);

    @Delete("delete from shopping_cart where commodity_id = #{commodity_id}")
    int deleteByCommodityId(@Param("commodity_id") long commodityId);

    @Insert("insert into shopping_cart (buyer_id, commodity_id, num, add_time) values (#{buyerId}, #{commodityId}, " +
            "#{num}, #{addTime})")
        //#{addTime, jdbcType=TIMESTAMP}
    int insert(ShoppingCart shoppingCart);

    @Select("select sum(num) from shopping_cart where buyer_id = #{buyerId} and commodity_id= #{commodityId}")
    Optional<Integer> sumNumByBuyerIdAndCommodityId(@Param("buyerId") long buyerId, @Param("commodityId") long commodityId);


    @Select("select count(*) from shopping_cart where buyer_id = #{buyerId}")
    int countByBuyerId(@Param("buyerId") long buyerId);

    @Update("update shopping_cart set num = #{num}, add_time = #{add_time} where buyer_id = #{buyerId} and commodity_id= #{commodityId}")
    int updateNumByBuyerIdAndCommodityId(int num, @Param("buyerId") long buyerId, @Param("commodityId") long commodityId, @Param("add_time") Date addTime);

    @Select("select sum(c.price * s.num ) from commodity c, shopping_cart s where c.id = s.commodity_id and s.buyer_id = #{buyer_id}")
    float sumPrice(@Param("buyer_id") long buyerId);
}
