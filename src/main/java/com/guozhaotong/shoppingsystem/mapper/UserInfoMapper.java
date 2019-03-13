package com.guozhaotong.shoppingsystem.mapper;

import com.guozhaotong.shoppingsystem.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author 郭朝彤
 * @date 2019/3/1.
 */
@Component
public interface UserInfoMapper {
    @Select("select * from user_info where name = #{name}")
    UserInfo findByName(@Param("name") String name);
}
