package com.wh.bank;

import com.wh.bank.entity.User;
import com.wh.bank.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class TestUser {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){
        User user = new User();
        user.setLoginName("admin01");
        user.setPassword("admin01");
        user.setUserName("程楠");
        user.setCreateTime(new Date());
        userMapper.insert(user);
    }
//查询并打印
@Test
    public void test02(){
        User user=userMapper.findUserByLoginName("admin");
        System.out.println(user);

}

}
