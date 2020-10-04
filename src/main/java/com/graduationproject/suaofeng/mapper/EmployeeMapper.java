package com.graduationproject.suaofeng.mapper;

import com.graduationproject.suaofeng.entities.Employee;

import java.util.List;

public interface EmployeeMapper {

    //根据员工名获取员工
    Employee getEmployeeByName(String ename);
    //根据Email获取员工
    public Employee getEmployeeByEmail(String email);
    //根据部门id获取员工
    List<Employee>  getEmployeeByDid(int did);
    //获取所有的员工
    List<Employee> getEmployees();
    //根据id删除员工
    void delById(int eid);
    //添加员工
    void addEmp(Employee employee);
    //通过uid获取员工信息
    Employee getEmployeeByUid(int uid);
    //更新信息
    void updateEmp(Employee employee);
    //上传头像
    void setAvatar(Employee employee);
    //获取员工
    Employee getEmployeeByEid(int eid);
    //模糊查找
    List<Employee> getLikeEmployeeByName(String ename);
}
