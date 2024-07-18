package com.wh.bank.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wh.bank.entity.Payee;
import com.wh.bank.entity.TradeInfo;
import com.wh.bank.service.AccountService;
import com.wh.bank.service.PayeeService;
import com.wh.bank.service.TradeInfoService;
import com.wh.bank.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.spi.DirStateFactory;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class TradeInfoController {
    @Autowired
    private TradeInfoService tradeInfoService;

    @Autowired
    private PayeeService payeeService;

    @GetMapping("/trades")
    public Result pageTradeInfo(Integer page, String fromAccountId){
        IPage<TradeInfo> tradeInfoIPage = tradeInfoService.pageTradeInfo(page, fromAccountId);
        return Result.ok("交易查询成功",tradeInfoIPage);
    }
}
