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
}
