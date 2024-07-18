package com.wh.bank.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @TableName deposit
 */
@TableName(value ="deposit")
@Data
public class Deposit implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer depositId;

    private String accountId;

    private String depositType;

    private Integer remaining;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date depositTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expireTime;

    private BigDecimal interestRate;

    private Integer delFlag;

    private String remark;

    private static final long serialVersionUID = 1L;
}