package com.graduationproject.suaofeng.service;

import com.graduationproject.suaofeng.entities.Department;

import java.util.List;

public interface DepartmentService {
    //获取部门表
    List<Department> getDeps();
    //增加部门
    void addDept(Department department);
    //通过ID获取部门
    List<Department> getDepById(int did);
    //通过部门名称获取部门
    List<Department> getDepByName(String dname);

    void delById(int did);
}
