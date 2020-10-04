package com.graduationproject.suaofeng.service;

import com.graduationproject.suaofeng.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    public List<User> getUsers();
    //根据密码用户名获取用户
    public User getUser(String username,String password);
    //根据用户名获取用户
    public User getUserByName(String username);

    //添加用户
    public void addUser(User user);

}
