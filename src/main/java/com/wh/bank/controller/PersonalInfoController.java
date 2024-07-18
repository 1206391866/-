package com.wh.bank.controller;

import com.wh.bank.entity.Deposit;
import com.wh.bank.exception.APIException;
import com.wh.bank.service.DepositService;
import com.wh.bank.utils.Result;
import com.wh.bank.vo.PersonalDepositVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class PersonalInfoController {
    @Autowired
    private DepositService depositService;

    @GetMapping("/my/account/info")
    public Result myAccountInfo(String accountId){
        log.info("The current user accountId is {}", accountId);
        PersonalDepositVo personalDepositVo = new PersonalDepositVo();
        List<Deposit> depositList = depositService.getDepositListByAccount(accountId);
        if (depositList == null || depositList.size() == 0) {
            throw new APIException("账号没有存款信息");
        }
        for (Deposit deposit : depositList) {
            if ("活期".equals(deposit.getDepositType())) {
                personalDepositVo.setActiveRemaining(deposit.getRemaining());
            } else {
                personalDepositVo.setDeadRemaining(deposit.getRemaining());
            }
        }
        Deposit deposit = depositList.get(0);
        return Result.ok("查询个人存款信息成功", personalDepositVo);
    }

}
