package com.wh.bank.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName account
 */
@TableName(value ="account")
@Data
public class Account implements Serializable {
    @TableId
    private String accountId;

    private String password;

    private String identityName;

    private String mobile;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}