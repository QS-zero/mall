package com.exercise.mall.service;

import com.exercise.mall.pojo.User;
import com.exercise.mall.vo.ResponseVo;
import org.springframework.stereotype.Service;


public interface IUserService {
    /**
     * 注册
     */

    ResponseVo register(User user);



    /**
     * 登录
     */
}
