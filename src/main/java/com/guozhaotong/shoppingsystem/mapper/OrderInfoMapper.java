package com.guozhaotong.shoppingsystem.mapper;

import com.guozhaotong.shoppingsystem.entity.OrderInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 郭朝彤
 * @date 2019/3/1.
 */
public interface OrderInfoMapper {
    @Select("select * from order_info where buyer_id = #{buyer_id}")
    List<OrderInfo> findByBuyerId(@Param("buyer_id") long buyerId);

    @Select("select sum(num*price_when_buy) from order_info where buyer_id = #{buyer_id}")
    float sumPrice(@Param("buyer_id") long buyerId);

    @Delete("delete from order_info where id = #{id}")
    int deleteById(@Param("id") long id);

    @Insert("insert into order_info(buyer_id, commodity_id, finish_time, price_when_buy) values (#{buyerId}, #{commodityId}, " +
            "#{finishTime}, #{num}, #{priceWhenBuy})") //#{priceWhenBuy, jdbcType=TIMESTAMP}
    int insert(OrderInfo orderInfo);
}
