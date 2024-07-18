package com.wh.bank.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wh.bank.entity.Account;
import com.wh.bank.entity.Deposit;
import com.wh.bank.entity.TradeInfo;
import com.wh.bank.exception.APIException;
import com.wh.bank.mapper.AccountMapper;
import com.wh.bank.mapper.DepositMapper;
import com.wh.bank.mapper.TradeInfoMapper;
import com.wh.bank.vo.TradeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DepositService {

    @Autowired
    private DepositMapper depositMapper;

    @Autowired
    private TradeInfoMapper tradeInfoMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Transactional
    public void tradeDeposit(TradeVo tradeVo){
        //转出账户信息
        Deposit fromDeposit = depositMapper.getDepositByAccountId(tradeVo.getFromAccountId());
        if (fromDeposit == null){
            throw new APIException("错误的请求");
        }
        //转入账户的信息是否正确
        Deposit toDeposit = depositMapper.getDepositByAccountId(tradeVo.getToAccountId());
        if (toDeposit == null){
            throw new APIException("错误的请求");
        }
        Account toAccount = accountMapper.selectById(tradeVo.getToAccountId());
        if (toAccount==null){
            throw new APIException("错误的请求");
        }


        //账户的金额是否足够
        if(fromDeposit.getRemaining()< tradeVo.getMoney()){
            throw new APIException("账户金额不足");
        }

        //扣除账户的金额
        depositMapper.subDepositMoney(tradeVo.getFromAccountId(),tradeVo.getMoney());

        //添加账户的金额
        depositMapper.addDepositMoney(tradeVo.getToAccountId(),tradeVo.getMoney());

        //J记录日志
        TradeInfo tradeInfo =new TradeInfo();
        tradeInfo.setTradeType("同行转账");
        tradeInfo.setFromAccount(tradeVo.getFromAccountId());
        tradeInfo.setToAccount(tradeVo.getToAccountId());
        tradeInfo.setTradeMoney(tradeVo.getMoney());
        tradeInfo.setTradeTime(new Date());
        tradeInfo.setSummary("您向"+ toAccount.getIdentityName()+"转账"+tradeVo.getMoney()+"分。备注是："+ tradeVo.getRemark());
        tradeInfoMapper.insert(tradeInfo);
    }

    public void addDeposit(Deposit deposit) {
        depositMapper.insert(deposit);
    }

    public void updateDeposit(Deposit deposit) {
        depositMapper.updateById(deposit);
    }

    public List<Deposit> getDepositListByAccount(String accountId, String depositType) {
        QueryWrapper<Deposit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account_id", accountId);
        queryWrapper.eq("deposit_type", depositType);
        return depositMapper.selectList(queryWrapper);
    }

    public List<Deposit> getDepositListByAccount(String accountId) {
        QueryWrapper<Deposit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account_id", accountId);
        return depositMapper.selectList(queryWrapper);
    }
}
