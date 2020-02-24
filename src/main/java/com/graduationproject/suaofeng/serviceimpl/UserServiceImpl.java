package com.graduationproject.suaofeng.serviceimpl;

import com.graduationproject.suaofeng.entities.User;
import com.graduationproject.suaofeng.mapper.UserMapper;
import com.graduationproject.suaofeng.service.UserService;
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
}
