package com.wh.bank.mapper;

import com.wh.bank.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author 楠
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-07-06 15:27:31
* @Entity com.wh.bank.entity.User
*/
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where login_name = #{loginName}")
    public User findUserByLoginName(@Param("loginName")String loginName);

public User selectByLoginNameUser(String logName);

}




