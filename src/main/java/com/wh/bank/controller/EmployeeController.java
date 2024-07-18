package com.wh.bank.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wh.bank.entity.Employee;
import com.wh.bank.service.EmployeeService;
import com.wh.bank.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/emps/{page}")
    public Result<IPage<Employee> > pageEmployee(@PathVariable("page")  Integer page,
                                                  @RequestParam(value = "name",required = false, defaultValue = "") String name){
        IPage<Employee> pe =employeeService.pageEmployee(page,name);
        return Result.ok("查询员工列表成功",pe);
    }

    @GetMapping("/emp/{empId}")
    public Result<Employee> getEmployee(@PathVariable("empId") Integer empId){
        Employee employee =employeeService.getEmployee(empId);
        return Result.ok("查询员工成功",employee);
    }

    @PostMapping("/emp")
    public Result addEmployee(Employee employee){
        log.info("===addEmployee==", employee);
        employeeService.addEmployee(employee);
        return Result.ok("成功添加员工");
    }

    @PutMapping("/emp")
    public Result upateEmployee(@RequestBody Employee employee){
        log.info("===upateEmployee==", employee);
        employeeService.updateEmployee(employee);
        return Result.ok("成功修改员工");
    }

    @DeleteMapping("/emp/{empId}")
    public Result deleteEmployee(@PathVariable("empId")Integer empId){
        employeeService.deleteEmployee(empId);
        return Result.ok("成功删除员工");
    }

}
