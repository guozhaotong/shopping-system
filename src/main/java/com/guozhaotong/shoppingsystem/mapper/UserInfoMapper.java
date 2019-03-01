package com.guozhaotong.shoppingsystem.mapper;

import com.guozhaotong.shoppingsystem.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 郭朝彤
 * @date 2019/3/1.
 */
@Mapper
public interface UserInfoMapper {
    @Select("select * from user where name = #{name}")
    UserInfo findByName(@Param("name") String name);
}
