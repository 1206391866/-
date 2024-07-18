package com.wh.bank.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @TableName employee
 */
@TableName(value ="employee")
@Data
public class Employee implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer empId;

    private String empName;

    private String gender;

    private BigDecimal salary;

    private String jobTitle;

    private static final long serialVersionUID = 1L;
}