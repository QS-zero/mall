package com.exercise.mall.controller;


import com.exercise.mall.consts.MallConst;
import com.exercise.mall.form.OrderCreateForm;
import com.exercise.mall.pojo.User;
import com.exercise.mall.service.IOrderService;
import com.exercise.mall.vo.OrderVo;
import com.exercise.mall.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.PublicKey;

@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;


    @PostMapping("/orders")
    public ResponseVo<OrderVo> create(@Valid @RequestBody OrderCreateForm form,
                                      HttpSession session){

        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        Integer uid = user.getId();
        ResponseVo<OrderVo> responseVo = orderService.create(uid, form.getShippingId());

        return responseVo;
    }

    @GetMapping("/orders")
    public ResponseVo<PageInfo> list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                         HttpSession session){

        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        Integer uid = user.getId();

        ResponseVo<PageInfo> pageInfoList = orderService.list(uid, pageNum, pageSize);

        return pageInfoList;
    }

    @GetMapping("/orders/{orderNo}")
    public ResponseVo<OrderVo> detail(@PathVariable Long orderNo,
                                      HttpSession session){

        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        Integer uid = user.getId();

        ResponseVo<OrderVo> responseVo = orderService.detail(uid, orderNo);

        return responseVo;
    }

    @PutMapping("/orders/{orderNo}")
    public ResponseVo cancel(@PathVariable Long orderNO,
                                      HttpSession session){

        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        Integer uid = user.getId();

        ResponseVo responseVo = orderService.cancel(uid, orderNO);

        return responseVo;

    }
}
