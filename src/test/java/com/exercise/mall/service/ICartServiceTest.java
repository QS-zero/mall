package com.exercise.mall.service;

import com.exercise.mall.MallApplicationTest;
import com.exercise.mall.form.CartAddForm;
import com.exercise.mall.form.CartUpdateForm;
import com.exercise.mall.pojo.Cart;
import com.exercise.mall.vo.CartVo;
import com.exercise.mall.vo.ResponseVo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

@Slf4j
public class ICartServiceTest extends MallApplicationTest {

    @Autowired
    private ICartService cartService;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Test
    public void add(){
        CartAddForm form = new CartAddForm();
        form.setProductId(29);
        form.setSelected(true);

        ResponseVo<CartVo> list = cartService.add(1, form);

        log.info("list:{}", gson.toJson(list));

    }

    @Test
    public void list(){
        ResponseVo<CartVo> list = cartService.list(1);
        log.info("list:{}", gson.toJson(list));
    }

    @Test
    public void update(){
        CartUpdateForm form = new CartUpdateForm();
        form.setQuantity(10);
        form.setSelected(false);
        ResponseVo<CartVo> list = cartService.update(1, 27, form);
        log.info("result:{}", gson.toJson(list));
    }

    @Test
    public void delete(){
        ResponseVo<CartVo> list = cartService.delete(1, 26);
        log.info("result:{}", gson.toJson(list));
    }
}