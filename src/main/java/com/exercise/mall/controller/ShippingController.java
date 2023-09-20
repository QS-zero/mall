package com.exercise.mall.controller;


import com.exercise.mall.consts.MallConst;
import com.exercise.mall.form.ShippingForm;
import com.exercise.mall.pojo.User;
import com.exercise.mall.service.IShippingService;
import com.exercise.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class ShippingController {

    @Autowired
    private IShippingService shippingService;

    @PostMapping("/shippings")
    public ResponseVo add(@Valid @RequestBody ShippingForm form,
                          HttpSession session){

        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        Integer uid = user.getId();

        return shippingService.add(uid, form);
    }

    @DeleteMapping("/shippings/{shippingId}")
    public ResponseVo delete(@PathVariable Integer shippingId,
                             HttpSession session){

        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        Integer uid = user.getId();

        return shippingService.delete(uid, shippingId);
    }

    @PutMapping("/shippings/{shippingId}")
    public ResponseVo update(@PathVariable Integer shippingId,
                             @Valid @RequestBody ShippingForm form,
                             HttpSession session){

        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        Integer uid = user.getId();

        return shippingService.update(uid, shippingId, form);
    }


    @GetMapping("/shippings")
    public ResponseVo list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                           HttpSession session){

        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        Integer uid = user.getId();

        return shippingService.list(uid, pageNum, pageSize);
    }
}
