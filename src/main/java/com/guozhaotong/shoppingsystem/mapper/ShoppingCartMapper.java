package com.guozhaotong.shoppingsystem.mapper;

import com.guozhaotong.shoppingsystem.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 郭朝彤
 * @date 2019/3/1.
 */
public interface ShoppingCartMapper {
    @Select("select * from shopping_cart where buyer_id = #{buyer_id}")
    List<ShoppingCart> findByBuyerId(@Param("buyer_id") long buyerId);

    @Delete("delete from shopping_cart where id = #{id}")
    int deleteById(long id);

    @Insert("insert into shopping_cart (buyer_id, commodity_id, num, add_time) values (#{buyerId}, #{commodityId}, " +
            "#{num}, #{addTime})") //#{addTime, jdbcType=TIMESTAMP}
    int insert(ShoppingCart shoppingCart);

    @Select("select count(*) from shopping_cart where buyer_id = #{buyerId} and commodity_id= #{commodityId}")
    int countBuyerIdAndCommodityId(long buyerId, long commodityId);

    @Update("update shopping_cart set num = #{num} where buyer_id = #{buyerId} and commodity_id= #{commodityId}")
    int updateNumByBuyerIdAndCommodityId(int num, long buyerId, long commodityId);

}
