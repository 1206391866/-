package com.wh.bank;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wh.bank.entity.Employee;
import com.wh.bank.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest //测试类
class BankApplicationTests {
@Autowired
    private EmployeeMapper employeeMapper;
    @Test
  public   void test01() {
        Employee employee = new Employee();
        employee.setEmpName("李雷");
        employee.setGender("男");
    employee.setSalary(new BigDecimal(8000));
    employee.setJobTitle("初级程序员");
    employeeMapper.insert(employee);
    }
@Test
    public void test02(){
    Employee employee = new Employee();
    employee.setEmpId(3);
    employee.setEmpName("程楠");
    employee.setGender("男");
    employee.setSalary(new BigDecimal(7000));
    employee.setJobTitle("初级程序员");
    employeeMapper.updateById(employee);
}
@Test
public void  test03(){
        Employee employee = employeeMapper.selectById(2);
        System.out.println(employee);
}

    @Test
    public void  test04(){
        List<Employee> employees =employeeMapper.selectList(null);
        for (Employee employee : employees){
            System.out.println(employee);
        }
    }
    @Test
    public  void test05(){
    employeeMapper.deleteById(2);
    }

    // 分页查询男性员工，并按ID降序排列，打印结果
    @Test
    public void test06(){
        int pageSize = 4;
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<Employee>();
        queryWrapper.eq("gender","男");
        queryWrapper.orderByDesc("emp_id");
        Page<Employee> p = new Page<Employee>(1,pageSize);
        IPage<Employee> pe = employeeMapper.selectPage(p,queryWrapper);
        System.out.println("总页数" + pe.getPages());
        System.out.println("总记录条数" + pe.getTotal());
        System.out.println("当前页" + pe.getCurrent());
        System.out.println("一页显示多少条记录" + pe.getSize());
        for (Employee employee : pe.getRecords()){
            System.out.println(employee);
        }
    }

    // 查询名字中包含“张”、性别为男、工资大于3000的员工并打印
    @Test
    public void test07(){
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<Employee>();
        queryWrapper.like("emp_name", "张");
        queryWrapper.eq("gender","男");
        queryWrapper.gt("salary",3000);
        List<Employee> employees = employeeMapper.selectList(queryWrapper);
        for (Employee employee : employees){
            System.out.println(employee);
        }
    }

}
