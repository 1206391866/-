package com.wh.bank.vo;

import lombok.Data;

@Data

public class TradeVo {
    private  String fromAccountId;
    private  String toAccountId;
    private  Integer money;
    private  String remark;

}
