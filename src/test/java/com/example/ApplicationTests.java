package com.example;

import com.example.domain.User;
import com.example.mappers.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
public class ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findAllUsers() {
        List<User> users = userMapper.findAllUsers();
        assertNotNull(users);
        assertTrue(!users.isEmpty());
    }

    @Test
    public void findUserById() {
        User user = userMapper.findUserById(1);
        assertNotNull(user);
    }

    @Test
    public void createUser() {
        User user = new User(0, "John", "john@gmail.com");
        userMapper.insertUser(user);
        User newUser = userMapper.findUserById(user.getId());
        assertEquals("John", newUser.getName());
        assertEquals("john@gmail.com", newUser.getEmail());
    }
}
