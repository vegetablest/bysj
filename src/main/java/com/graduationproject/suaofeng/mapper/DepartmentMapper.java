package com.graduationproject.suaofeng.mapper;

import com.graduationproject.suaofeng.entities.Department;

import java.util.List;

public interface DepartmentMapper {
    //获取部门表
    List<Department> getDeps();
    //添加部门
    void addDept(Department department);
    //通过Id查找部门
    List<Department> getDepById(int did);
    //通过部门名查找部门
    List<Department> getDepByName(String dname);
    //通过id删除部门
    void delById(int did);
}
