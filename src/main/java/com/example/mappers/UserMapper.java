package com.example.mappers;


import java.util.List;

import com.example.domain.User;

public interface UserMapper {

    void insertUser(User user);

    User getUserById(Integer userId);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(Integer userId);

}
