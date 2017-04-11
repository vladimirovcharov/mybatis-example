package com.example.service;

import java.util.List;

import com.example.domain.User;
import com.example.mappers.UserMapper;
import com.example.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserServiceTest {
    private static UserService userService;

    @BeforeClass
    public static void setup() {
        userService = new UserService();
    }

    @AfterClass
    public static void tearDown() {
//        userService.deleteAllUsers();
        userService = null;
    }

    @Test
    public void getUserById() {
        User userById = userService.getUserById(1);
        System.out.println(userById);
    }

    @Test
    public void testGetUserById() {
        int userId = insertUser();
        User user;

        user = userService.getUserById(userId);
        Assert.assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void testGetAllUsers() {
        for (int i = 0; i < 5; i++) {
            insertUser();
        }
        List<User> users = userService.getAllUsers();
        Assert.assertNotNull(users);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setEmailId("test_email_" + System.currentTimeMillis() + "@gmail.com");
        user.setPassword("secret");
        user.setFirstName("TestFirstName");
        user.setLastName("TestLastName");

        userService.insertUser(user);
        Assert.assertTrue(user.getUserId() != 0);
        User createdUser = userService.getUserById(user.getUserId());
        Assert.assertNotNull(createdUser);
        Assert.assertEquals(user.getEmailId(), createdUser.getEmailId());
        Assert.assertEquals(user.getPassword(), createdUser.getPassword());
        Assert.assertEquals(user.getFirstName(), createdUser.getFirstName());
        Assert.assertEquals(user.getLastName(), createdUser.getLastName());

    }

    @Test
    public void testUpdateUser() {
        int userId = insertUser();

        long timestamp = System.currentTimeMillis();
        User user = userService.getUserById(userId);
        user.setFirstName("TestFirstName" + timestamp);
        user.setLastName("TestLastName" + timestamp);
        userService.updateUser(user);
        User updatedUser = userService.getUserById(userId);
        Assert.assertEquals(user.getFirstName(), updatedUser.getFirstName());
        Assert.assertEquals(user.getLastName(), updatedUser.getLastName());
    }

    @Test
    public void testDeleteUser() {
        int userId = insertUser();

        User user = userService.getUserById(userId);
        userService.deleteUser(user.getUserId());
        User deletedUser = userService.getUserById(userId);
        Assert.assertNull(deletedUser);

    }

    private int insertUser() {
        User user = new User();
        user.setEmailId("test_email_" + System.currentTimeMillis() + "@gmail.com");
        user.setPassword("secret");
        user.setFirstName("TestFirstName");
        user.setLastName("TestLastName");

        userService.insertUser(user);
        int userId = user.getUserId();
        Assert.assertTrue(userId != 0);
        User createdUser = userService.getUserById(user.getUserId());
        Assert.assertNotNull(createdUser);
        return userId;
    }
}