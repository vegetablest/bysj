package com.graduationproject.suaofeng.serviceimpl;

import com.graduationproject.suaofeng.entities.Employee;
import com.graduationproject.suaofeng.entities.User;
import com.graduationproject.suaofeng.mapper.EmployeeMapper;
import com.graduationproject.suaofeng.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    //通过Email获取员工信息
    @Autowired(required = false)
    EmployeeMapper employeeMapper;
    @Override
    public Employee getEmployeeByEmail(String email) {
        Employee employee= employeeMapper.getEmployeeByEmail(email);
        return employee;
    }
    //通过员工名获取员工信息
    @Override
    public Employee getEmployeeByName(String ename) {
        return employeeMapper.getEmployeeByName(ename);
    }

    @Override
    public List<Employee> getEmployeeByDid(int did) {
        return employeeMapper.getEmployeeByDid(did);
    }

    //获取所有员工
    @Override
    public List<Employee> getEmployees() {
        return employeeMapper.getEmployees();
    }
    //根据id删除员工
    @Override
    public void delById(int eid) {
        employeeMapper.delById(eid);
    }
    //添加员工
    @Override
    public void addEmp(Employee employee) {
        employeeMapper.addEmp(employee);
    }
    //通过用户userid获取员工信息
    @Override
    public Employee getEmployeeByUid(int uid) {
        return employeeMapper.getEmployeeByUid(uid);
    }
    //更新员工信息
    @Override
    public void updateEmp(Employee employee) {
        employeeMapper.updateEmp(employee);
    }
    //上传头像
    @Override
    public void setAvatar(Employee employee) {
        employeeMapper.setAvatar(employee);
    }
    //获取id
    @Override
    public Employee getEmployeeByEid(int eid) {
        return employeeMapper.getEmployeeByEid(eid);
    }

    @Override
    public List<Employee> getLikeEmployeeByName(String ename) {

        return employeeMapper.getLikeEmployeeByName(ename);
    }
}
