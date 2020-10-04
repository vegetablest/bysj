package com.graduationproject.suaofeng.controller;

import com.graduationproject.suaofeng.entities.Department;
import com.graduationproject.suaofeng.entities.Employee;
import com.graduationproject.suaofeng.serviceimpl.DepartmentServiceImpl;
import com.graduationproject.suaofeng.serviceimpl.EmployeeServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class DepartmentController {

    @Autowired
    DepartmentServiceImpl departmentService;
    @Autowired
    EmployeeServiceImpl employeeService;
    //去部门管理页面
    @RequestMapping("/department/getdeps")
    public String getdeps(Model model){
        List<Department> deps = departmentService.getDeps();
        model.addAttribute("deps",deps);
        return "department";
    }
    //去部门添加页面
    @RequestMapping("/department/addpage")
    public String toaddpage(Model model){
        List<Employee> employees =  employeeService.getEmployees();
        model.addAttribute("employees",employees);
        return "dept/addpage";
    }
    //部门添加
    @PostMapping("/dep/adddep")
    public String addpage(Department department, HttpSession session){
        int did = department.getDid();
        String dname = department.getDname();
        if (did<1000){
            session.setAttribute("messages","部门编号不合格，请更换后重试");
            //重新转发到本页面
            return "redirect:/department/addpage";
        }
       List<Department> department1 = departmentService.getDepById(did);
        if (department1.size() > 0){
            session.setAttribute("messages","部门编号已存在，请更换后重试");
            //重新转发到本页面
            return "redirect:/department/addpage";
        }
        List<Department> departments = departmentService.getDepByName(dname);
        if (departments.size() >0 ){
            session.setAttribute("messages","该部门已存在，请更换后重试");
            //重新转发到本页面
            return "redirect:/department/addpage";
        }
        String supervisorname = department.getSupervisorname();
        Employee employee = employeeService.getEmployeeByName(supervisorname);
        Integer eid = employee.getEid();
        department.setSupervisorid(eid);
        departmentService.addDept(department);
        //重定向去此页面，不要用模板引擎去这个页面了
        return "redirect:/department/getdeps";
    }

    @DeleteMapping("/dep/{did}")
    public String deldep(@PathVariable("did") int did){
        departmentService.delById(did);
        return "redirect:/department/getdeps";
    }
}
