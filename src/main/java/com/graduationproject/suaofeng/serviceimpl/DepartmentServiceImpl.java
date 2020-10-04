package com.graduationproject.suaofeng.serviceimpl;

import com.graduationproject.suaofeng.entities.Department;
import com.graduationproject.suaofeng.mapper.DepartmentMapper;
import com.graduationproject.suaofeng.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired(required = false)
    DepartmentMapper departmentMapper;
    //获取部门表
    @Override
    public List<Department> getDeps() {
        return departmentMapper.getDeps();
    }

    @Override
    public void addDept(Department department) {
        departmentMapper.addDept(department);
    }

    @Override
    public List<Department> getDepById(int did) {
        return departmentMapper.getDepById(did);
    }

    @Override
    public List<Department> getDepByName(String dname) {
        return departmentMapper.getDepByName(dname);
    }
    //根据id删除部门
    @Override
    public void delById(int did) {
        departmentMapper.delById(did);
    }
    //
}
