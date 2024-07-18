package com.wh.bank.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wh.bank.entity.Payee;
import com.wh.bank.exception.APIException;
import com.wh.bank.service.PayeeService;
import com.wh.bank.utils.Result;
import com.wh.bank.vo.PayeeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class PayeeController {

    @Autowired
    private PayeeService payeeService;


    @GetMapping("/payees")
    public Result pageTradeInfo(Integer page, String fromAccountId){
        IPage<Payee> payeeIPage = payeeService.pagePayee(page, fromAccountId);
        return Result.ok("收款人查询成功", payeeIPage);
    }

    @PostMapping("/addPayee")
    public Result addPayee(Payee payee){
        log.info("===addPayee==", payee);
        payee.setCreateTime(new Date());
        payeeService.addPayee(payee);
        return Result.ok("成功添加收款人");
    }

    @GetMapping("/deletePayee")
    public Result deletePayee(int payeeId) {
        Payee payee = payeeService.getPayeeById(payeeId);
        if (payee == null) {
            throw new APIException("收款人不存在");
        }
        payeeService.deletePayee(payeeId);
        return Result.ok("成功删除收款人");
    }

    @GetMapping("/payee/list")
    public Result getPayeeListByAccountId(String fromAccountId) {
        List<PayeeVo> payeeVos = new ArrayList<>();
        List<Payee> payeeList = payeeService.getPayeeList(fromAccountId);
        if (payeeList != null && payeeList.size() > 0) {
            for (Payee payee : payeeList) {
                PayeeVo payeeVo = new PayeeVo();
                payeeVo.setText(payee.getIdentityName());
                payeeVo.setValue(payee.getToAccountId());
                payeeVos.add(payeeVo);
            }
        }
        return Result.ok("获取收款人列表成功", payeeVos);
    }
}
