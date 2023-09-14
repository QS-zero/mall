package com.exercise.mall.service;


import com.exercise.mall.form.CartAddForm;
import com.exercise.mall.vo.CartVo;
import com.exercise.mall.vo.ResponseVo;

public interface ICartService {

    ResponseVo<CartVo> add(Integer uid,CartAddForm form);

    ResponseVo<CartVo> list(Integer uid);
}
