package com.wh.bank.service;

import com.wh.bank.entity.Account;
import com.wh.bank.entity.Deposit;
import com.wh.bank.exception.APIException;
import com.wh.bank.mapper.AccountMapper;
import com.wh.bank.mapper.DepositMapper;
import com.wh.bank.utils.DateTools;
import com.wh.bank.vo.AccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;

    //登录
    public Account loginAccount(String accountId,String pasword) {
        Account account= accountMapper.selectById(accountId);
        if(account == null){
            throw new APIException("银行账号不存在");
        }
        if(!pasword.equals(account.getPassword())){
            throw new APIException("密码不正确");
        }
        return account;
    }


    @Autowired
    private DepositMapper depositMapper;
    //注册
    @Transactional //说明这个方法是事务方法
    public  void registerAccount(AccountVo accountVo){

        //1.acc加一个记录
        Account account =new Account();
        account.setAccountId(accountVo.getAccountId());
        account.setPassword(accountVo.getAccountId());
        account.setIdentityName(accountVo.getIdentityName());
        account.setMobile(accountVo.getAccountId());
        account.setCreateTime(new Date());
        accountMapper.insert(account);



        //2.在dep表加一条活期为0的记录
        Deposit deposit =new Deposit();
        deposit.setAccountId(accountVo.getAccountId());
        deposit.setDepositType("活期");
        deposit.setRemaining(0);
        deposit.setDepositTime(new Date());
        deposit.setExpireTime(DateTools.getYearTime(99));
        deposit.setInterestRate(new BigDecimal(0.002));
        deposit.setDelFlag(0);
        deposit.setRemark(accountVo.getIdentityName()+"新建银行账户");
       depositMapper.insert(deposit);
    }
}
