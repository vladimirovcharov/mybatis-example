package com.example.mappers;

import com.example.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into users(name,email) values(#{name},#{email})")
    @SelectKey(statement = "call identity()", keyProperty = "id", before = false, resultType = Integer.class)
    void insertUser(User user);

    @Select("select id, name, email from users WHERE id=#{id}")
    User findUserById(Integer id);

    @Select("select id, name, email from users")
    List<User> findAllUsers();
}
