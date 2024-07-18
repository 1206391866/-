package com.wh.bank.mapper;

import com.wh.bank.entity.Deposit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
* @author 楠
* @description 针对表【deposit】的数据库操作Mapper
* @createDate 2024-07-06 15:27:31
* @Entity com.wh.bank.entity.Deposit
*/
public interface DepositMapper extends BaseMapper<Deposit> {

    @Select("select * from deposit where account_id=#{accountId} and deposit_type='活期'")
    public Deposit getDepositByAccountId(@Param("accountId") String accountId);

    @Update("update deposit set remaining=remaining - #{money} where account_id=#{fromAccountId} and deposit_type='活期' ")
    public  void subDepositMoney(@Param("fromAccountId") String fromAccountId,@Param("money")  Integer money);

    @Update("update deposit set remaining=remaining + #{money} where account_id=#{toAccountId} and deposit_type='活期' ")
    public  void addDepositMoney(@Param("toAccountId") String toAccountId,@Param("money")  Integer money);
}




