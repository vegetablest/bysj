package com.graduationproject.suaofeng;

import com.graduationproject.suaofeng.entities.User;
import com.graduationproject.suaofeng.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class SuaofengApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        List<User> users = userService.getUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    void test() {
        User user = userService.getUser("张三","123456");
        System.out.println(user);
    }
    @Test
    void test02() {
        User user = userService.getUserByName("张三");
        System.out.println(user);
    }
    @Test
    void test01() {
        User user = new User();
        user.setUsername("李四");
        user.setPassword("123456");
        userService.addUser(user);
    }
}
