package com.guozhaotong.shoppingsystem.mapper;

import com.guozhaotong.shoppingsystem.entity.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author 郭朝彤
 * @date 2019/3/1.
 */
@Component
public interface OrderInfoMapper {
    @Select("select * from order_info where buyer_id = #{buyer_id} order by finish_time desc")
    List<OrderInfo> findByBuyerId(@Param("buyer_id") long buyerId);

    @Select("select sum(num*price_when_buy) from order_info where buyer_id = #{buyer_id}")
    Optional<Float> sumPrice(@Param("buyer_id") long buyerId);

    @Select("select count(*) from order_info where commodity_id = #{commodityId} and buyer_id = #{buyer_id}")
    Optional<Integer> countByCommodityIdAndBuyerId(@Param("commodityId") long commodityId, @Param("buyer_id") long buyerId);

    @Insert("insert into order_info(buyer_id, commodity_id, finish_time, num, price_when_buy) values (#{buyerId}, #{commodityId}, " +
            "#{finishTime}, #{num}, #{priceWhenBuy})")
        //#{priceWhenBuy, jdbcType=TIMESTAMP}
    int insert(OrderInfo orderInfo);

    @Select("select count(*) from order_info where commodity_id = #{commodityId}")
    Optional<Integer> countByCommodityId(@Param("commodityId") long commodityId);

    @Select("select * from order_info where commodity_id = #{commodityId} order by finish_time desc limit 1")
    OrderInfo findByCommodityId(@Param("commodityId") long commodityId);
}
