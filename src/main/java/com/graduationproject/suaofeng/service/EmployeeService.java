package com.graduationproject.suaofeng.service;

import com.graduationproject.suaofeng.entities.Employee;
import com.graduationproject.suaofeng.entities.User;

import java.util.List;

public interface EmployeeService {
    //根据邮箱地址获取用户
    public Employee getEmployeeByEmail(String email);
    //根据用户名获取员工
    Employee getEmployeeByName(String ename);
    //根据部门id获取用户
    List<Employee> getEmployeeByDid(int did);
    //获取所有员工
    List<Employee> getEmployees();
    //删除员工根据id
    void delById(int eid);
    //添加员工
    void addEmp(Employee employee);
    //通过uid获取员工信息
    Employee getEmployeeByUid(int uid);
    //更新信息
    void updateEmp(Employee employee);
    //上传头像
    void setAvatar(Employee employee);
    //根据id获取员工
    Employee getEmployeeByEid(int eid);
    //模糊查找
    List<Employee> getLikeEmployeeByName(String ename);
}
