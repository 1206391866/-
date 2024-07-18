package com.wh.bank.controller;

import com.wh.bank.entity.Account;
import com.wh.bank.service.AccountService;
import com.wh.bank.utils.Result;
import com.wh.bank.vo.AccountVo;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin//服务端解决跨域问题
public class LoginController {

    @Autowired
    private AccountService accountService;


    @PostMapping("/login")
    public Result loginAccount(@RequestParam("accountId")String accountId, @RequestParam("password")String password){
      log.info("THE loginAccout is{}，Password is()",accountId,password);
        Account account=accountService.loginAccount(accountId,password);
        return  Result.ok("登陆成功",account);

    }

    @PostMapping("/account/register")
    public Result registerAccount(@Valid AccountVo accountVo, BindingResult bindingResult){
        log.info("THE regist Account is {}", accountVo);
        if (bindingResult.getErrorCount() > 0) {
            StringBuilder str=new StringBuilder("校验出错：");
            for (ObjectError error :bindingResult.getAllErrors()) {
                str.append(error.getDefaultMessage()+";");
            }
            return Result.error(str.toString());
        }
       accountService.registerAccount(accountVo);
       return Result.ok("注册成功", accountVo);
    }
}
