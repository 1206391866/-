package com.wh.bank.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName payee
 */
@TableName(value ="payee")
@Data
public class Payee implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer payeeId;

    private String accountId;

    private String toAccountId;

    private String identityName;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}