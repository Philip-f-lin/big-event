package org.philip.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.philip.pojo.User;

@Mapper
public interface UserMapper {
    // 根據用戶名查詢用戶
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    // 添加用戶名密碼
    @Insert("insert into user(username, password, create_time, update_time)" +
            " values(#{username}, #{password}, now(), now())")
    void add(String username, String password);


}