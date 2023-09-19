package com.exercise.mall.controller;


import com.exercise.mall.form.CartAddForm;
import com.exercise.mall.form.CartUpdateForm;
import com.exercise.mall.pojo.User;
import com.exercise.mall.service.ICartService;
import com.exercise.mall.vo.CartVo;
import com.exercise.mall.vo.ResponseVo;
import com.sun.javafx.geom.transform.CanTransformVec3d;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.exercise.mall.consts.MallConst.CURRENT_USER;

@RestController
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping("carts")
    public ResponseVo<CartVo> list(HttpSession session) {
        User user = (User) session.getAttribute(CURRENT_USER);
        Integer uid = user.getId();
        return cartService.list(uid);
    }

    @PostMapping("/carts")
    public ResponseVo<CartVo> add(@Valid @RequestBody CartAddForm cartAddForm,
                                  HttpSession session) {
        User user = (User) session.getAttribute(CURRENT_USER);
        Integer uid = user.getId();
        return cartService.add(uid, cartAddForm);
    }

    @PutMapping("/carts/{productId}")
    public ResponseVo<CartVo> update(@PathVariable Integer productId,
                                     @Valid @RequestBody CartUpdateForm cartUpdateForm,
                                     HttpSession session) {
        User user = (User) session.getAttribute(CURRENT_USER);
        Integer uid = user.getId();
        return cartService.update(uid, productId, cartUpdateForm);
    }

    @DeleteMapping("/carts/{productId}")
    public ResponseVo<CartVo> delete(@PathVariable Integer productId,
                                     HttpSession session) {
        User user = (User) session.getAttribute(CURRENT_USER);
        Integer uid = user.getId();
        return cartService.delete(uid, productId);
    }

    @PutMapping("/carts/selectAll")
    public ResponseVo<CartVo> selectAll(HttpSession session) {
        User user = (User) session.getAttribute(CURRENT_USER);
        Integer uid = user.getId();
        return cartService.selectAll(uid);
    }

    @PutMapping("/carts/unSelectAll")
    public ResponseVo<CartVo> unSelectAll(HttpSession session) {
        User user = (User) session.getAttribute(CURRENT_USER);
        Integer uid = user.getId();
        return cartService.unSelectAll(uid);
    }

    @GetMapping("/carts/products/sum")
    public ResponseVo<Integer> sum(HttpSession session) {
        User user = (User) session.getAttribute(CURRENT_USER);
        Integer uid = user.getId();
        return cartService.sum(uid);
    }


}
