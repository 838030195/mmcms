package com.jzz.webdemo.mapper;

import com.jzz.webdemo.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO `user` (`username`, `password`) VALUES (#{username}, #{password})")
    int register(User user);

    @Select("SELECT * FROM `user` WHERE `username`=#{username}")
    User getByUsername(@Param("username") String username);

    @Select("SELECT * FROM `user` WHERE `username`=#{username} AND `password`=#{password}")
    User auth(@Param("username") String username, @Param("password") String password);

    @Select("SELECT * FROM `user` WHERE `id`=#{id}")
    User get(@Param("id") int id);
}
