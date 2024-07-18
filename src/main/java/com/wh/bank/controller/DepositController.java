package com.wh.bank.controller;

import com.wh.bank.entity.Deposit;
import com.wh.bank.service.DepositService;
import com.wh.bank.utils.Result;
import com.wh.bank.vo.TradeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class DepositController {
    @Autowired
    private DepositService depositService;

    @PostMapping("/deposit/trade")
    public Result tradeDeposit(TradeVo tradeVo){
        log.info("THE tradeDeposit TradeVO is{}",tradeVo);
        depositService.tradeDeposit(tradeVo);
        return Result.ok("转账成功");
    }

    /**
     * 先查询是否有获取或者定期存款，没有则新增一个，有则更新
     * @param deposit
     * @return
     */
    @PostMapping("/deposit/update")
    public Result addDeposit(Deposit deposit) {
        List<Deposit> depositList = depositService.getDepositListByAccount(deposit.getAccountId(), deposit.getDepositType());
        if (depositList != null && depositList.size() > 0) {
            int remaining = depositList.get(0).getRemaining();
            int depositId = depositList.get(0).getDepositId();
            Deposit param = depositList.get(0);
            BeanUtils.copyProperties(deposit, param);
            param.setDepositId(depositId);
            param.setRemaining(param.getRemaining() + remaining);
            depositService.updateDeposit(param);
        } else {
            depositService.addDeposit(deposit);
        }
        return Result.ok("存款成功");
    }


}
