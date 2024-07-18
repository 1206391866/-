package com.wh.bank.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName trade_info
 */
@TableName(value ="trade_info")
@Data
public class TradeInfo implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer tradeId;

    private String tradeType;

    private String fromAccount;

    private String toAccount;

    private Integer tradeMoney;

    private Date tradeTime;

    private String summary;

    private static final long serialVersionUID = 1L;
}