package com.exercise.mall.Interceptor;

import com.exercise.mall.enums.ResponseEnum;
import com.exercise.mall.exception.UserLoginException;
import com.exercise.mall.pojo.User;
import com.exercise.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.exercise.mall.consts.MallConst.CURRENT_USER;


@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {

    /**
     * true 表示继续流程, false 表示中断流程
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle...");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(CURRENT_USER);
        if (user == null) {
            log.info("user = null");
            throw new UserLoginException();
        }
        return true;
    }
}
