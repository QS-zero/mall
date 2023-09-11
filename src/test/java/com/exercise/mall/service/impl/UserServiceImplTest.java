package com.exercise.mall.service.impl;

import com.exercise.mall.MallApplicationTest;
import com.exercise.mall.enums.ResponseEnum;
import com.exercise.mall.enums.RoleNum;
import com.exercise.mall.pojo.User;
import com.exercise.mall.service.IUserService;
import com.exercise.mall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional //事务，在Test起到回滚的作用
public class UserServiceImplTest extends MallApplicationTest {

    @Autowired
    private IUserService userService;

    @Before
    public void register() {
        User qs = new User("123", "123", "123@123.com", RoleNum.CUSTOMER.getCode());
        userService.register(qs);
    }

    @Test
    public void login(){
        ResponseVo<User> responseVo = userService.login("123", "123");
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());

    }

}