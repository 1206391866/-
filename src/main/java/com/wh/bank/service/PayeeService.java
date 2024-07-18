package com.wh.bank.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wh.bank.entity.Employee;
import com.wh.bank.entity.Payee;
import com.wh.bank.mapper.PayeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayeeService {

    @Autowired
    private PayeeMapper payeeMapper;


    public IPage<Payee> pagePayee(int page, String fromAccountId){
        QueryWrapper<Payee> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("account_id", fromAccountId);
        queryWrapper.orderByDesc("create_time");
        Page<Payee> p = new Page<>(page, 5);
        IPage<Payee>  pe = payeeMapper.selectPage(p, queryWrapper);
        return  pe;
    }

    public void addPayee(Payee payee){
        payeeMapper.insert(payee);
    }

    public void deletePayee(int payeeId) {
        Payee payee = new Payee();
        payee.setPayeeId(payeeId);
        payeeMapper.deleteById(payeeId);
    }

    public Payee getPayeeById(int payeeId) {
        return payeeMapper.selectById(payeeId);
    }

    public List<Payee> getPayeeList(String fromAccountId) {
        QueryWrapper<Payee> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("account_id", fromAccountId);
        List<Payee>  payeeList = payeeMapper.selectList(queryWrapper);
        return  payeeList;
    }
}
