package com.graduationproject.suaofeng.serviceimpl;

import com.graduationproject.suaofeng.entities.User;
import com.graduationproject.suaofeng.mapper.UserMapper;
import com.graduationproject.suaofeng.service.UserService;
import com.graduationproject.suaofeng.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public User getUser(String username, String password) {
        //先根据登陆名称查询登陆用户
        User existU = userMapper.getUser(username,Md5Utils.md5(password));
        return existU;
    }

    @Override
    public User getUserByName(String username) {
        User user=  userMapper.getUserByName(username);
       return user;
    }



    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

}
