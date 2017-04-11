package com.example.service;

import java.util.List;

import com.example.domain.User;
import com.example.mappers.UserMapper;
import com.example.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class UserService {

    public void insertUser(User user) {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.insertUser(user);
            sqlSession.commit();
        }
    }

    public User getUserById(Integer userId) {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            return userMapper.getUserById(userId);
        }
    }

    public List<User> getAllUsers() {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            return userMapper.getAllUsers();
        }
    }

    public void updateUser(User user) {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.updateUser(user);
            sqlSession.commit();
        }

    }

    public void deleteUser(Integer userId) {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.deleteUser(userId);
            sqlSession.commit();
        }

    }

    public void deleteAllUsers() {
        for (User user : getAllUsers()) {
            deleteUser(user.getUserId());
        }
    }

}
