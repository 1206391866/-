package com.wh.bank.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wh.bank.entity.TradeInfo;
import com.wh.bank.mapper.TradeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeInfoService {

    @Autowired
    private TradeInfoMapper tradeInfoMapper;

    public IPage<TradeInfo> pageTradeInfo(int page, String fromAccountId){
        QueryWrapper<TradeInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("from_account", fromAccountId);
        queryWrapper.orderByDesc("trade_time");
        Page<TradeInfo> p = new Page<>(page, 5);
        IPage<TradeInfo> pe = tradeInfoMapper.selectPage(p,queryWrapper);
        return  pe;
    }
}
