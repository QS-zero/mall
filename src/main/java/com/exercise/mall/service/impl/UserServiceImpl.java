package com.exercise.mall.service.impl;

import com.exercise.mall.dao.UserMapper;
import com.exercise.mall.enums.ResponseEnum;
import com.exercise.mall.enums.RoleNum;
import com.exercise.mall.pojo.User;
import com.exercise.mall.service.IUserService;
import com.exercise.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import sun.nio.cs.US_ASCII;

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
    public ResponseVo<User> register(User user) {

        //username不能重复
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if (countByUsername > 0) {
            return ResponseVo.error(ResponseEnum.USERNAME_EXIST);
        }

        //email不能重复
        int countByEmail = userMapper.countByEmail(user.getEmail());
        if (countByEmail > 0) {
            return ResponseVo.error(ResponseEnum.EMAIL_EXIST);

        }

        user.setRole(RoleNum.CUSTOMER.getCode());
        //Md5摘要算法(Spring自带）
        String MD5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
        user.setPassword(MD5Password);

        //写入数据库
        int resultCount = userMapper.insertSelective(user);
        if (resultCount == 0){
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success();
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     */
    @Override
    public ResponseVo<User> login(String username, String password) {
        User user = userMapper.selectByUsername(username);

        if (user == null){
            //用户不存在(返回用户名或密码错误)
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        if (!user.getPassword().equalsIgnoreCase(
                DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))) {
            //密码错误(返回用户名或密码错误)
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        user.setPassword("这能给你看？？？");
        return ResponseVo.success(user);
    }


}
