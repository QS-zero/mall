package com.exercise.mall.controller;


import com.exercise.mall.enums.ResponseEnum;
import com.exercise.mall.form.UserRegisterForm;
import com.exercise.mall.form.UserLoginForm;
import com.exercise.mall.pojo.User;
import com.exercise.mall.service.IUserService;
import com.exercise.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

import static com.exercise.mall.consts.MallConst.CURRENT_USER;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseVo<User> register(@Valid @RequestBody UserRegisterForm userRegisterForm,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("注册提交参数有误, {} {}",
                    Objects.requireNonNull(bindingResult.getFieldError()).getField(),
                    bindingResult.getFieldError().getDefaultMessage());

            return ResponseVo.error(ResponseEnum.PARAM_ERROR, bindingResult);
        }

        User user = new User();
        //SpringBoot 拷贝对象
        BeanUtils.copyProperties(userRegisterForm, user);
        //dto
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm,
                                  BindingResult bindingResult,
                                  HttpSession session){
        if (bindingResult.hasErrors()) {
            return ResponseVo.error(ResponseEnum.PARAM_ERROR, bindingResult);
        }

        ResponseVo<User> userResponseVo = userService.login(userLoginForm.getUsername(), userLoginForm.getUsername());
        // 设置Session
        session.setAttribute(CURRENT_USER, userResponseVo.getData());
        log.info("login sessionId: {}", session.getId());
        log.info("Session: {}", session);
        return userResponseVo;
    }

    //Session保存在内存,改进版本 token+redis
    @GetMapping("/getMsg")
    public ResponseVo<User> userInfo(HttpSession session){
        log.info("getMsg sessionId: {}", session.getId());

        User user = (User) session.getAttribute(CURRENT_USER);
        return ResponseVo.success(user);
    }


    //TODO 判断登陆状态(拦截器)
    /**
     * {@link TomcatServletWebServerFactory} getSessionTimeoutInMinutes
     */
    @PostMapping("/logout")
    public ResponseVo<User> Logout(HttpSession session){
        log.info("logout :{}", session);

        session.removeAttribute(CURRENT_USER);
        return ResponseVo.success();
    }
}
