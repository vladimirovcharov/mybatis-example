package com.example.mappers;

import com.example.domain.User;

import java.util.List;

public interface UserMapper {
    void insertUser(User user);

    User findUserById(Integer id);

    List<User> findAllUsers();
}
