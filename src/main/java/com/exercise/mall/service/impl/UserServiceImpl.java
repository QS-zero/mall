package com.exercise.mall.service.impl;

import com.exercise.mall.dao.UserMapper;
import com.exercise.mall.pojo.User;
import com.exercise.mall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 注册
     *
     * @param user
     */
    @Override
    public void register(User user) {

        //username不能重复
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if (countByUsername > 0) {
            throw new RuntimeException("该Username已经注册");
        }

        //email不能重复
        int countByEmail = userMapper.countByEmail(user.getEmail());
        if (countByEmail > 0) {
            throw new RuntimeException("该Email已经注册");
        }
        //Md5摘要算法(Spring自带）
        String MD5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
        user.setPassword(MD5Password);

        //写入数据库
        int resultCount = userMapper.insertSelective(user);
        if (resultCount == 0){
            throw new RuntimeException("注册失败");
        }
    }
}
