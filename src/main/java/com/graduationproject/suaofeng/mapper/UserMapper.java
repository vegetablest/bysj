package com.graduationproject.suaofeng.mapper;

import com.graduationproject.suaofeng.entities.Employee;
import com.graduationproject.suaofeng.entities.User;

import java.util.List;

public interface UserMapper {
    //获取用户列表
    public List<User> getUsers();
    //根据用户名和密码获取用户
    public User getUser(String username,String password);
    //根据用户名获取用户
    public User getUserByName(String username);
    //根据Email获取员工
    public Employee getEmployeeByEmail(String email);
    //添加用户
    public void addUser(User user);

}
