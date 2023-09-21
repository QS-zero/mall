package com.exercise.mall.service;

import com.exercise.mall.MallApplicationTest;
import com.exercise.mall.enums.ResponseEnum;
import com.exercise.mall.form.CartAddForm;
import com.exercise.mall.form.CartUpdateForm;
import com.exercise.mall.pojo.Cart;
import com.exercise.mall.vo.CartVo;
import com.exercise.mall.vo.ResponseVo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

@Slf4j
public class ICartServiceTest extends MallApplicationTest {

    @Autowired
    private ICartService cartService;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private Integer productAddId = 29;

    private Integer setQuantityNum = 8;


    private Integer uid = 1;

//    @Before
    @Test
    public void add(){
        log.info("【新增购物车】");
        CartAddForm form = new CartAddForm();
        form.setProductId(productAddId);
        form.setSelected(true);

        ResponseVo<CartVo> listResponseVo = cartService.add(uid, form);

        log.info("list:{}", gson.toJson(listResponseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), listResponseVo.getStatus());


    }

    @Test
    public void list(){
        ResponseVo<CartVo> list = cartService.list(uid);
        log.info("list:{}", gson.toJson(list));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), list.getStatus());



    }

    @Test
    public void update(){
        CartUpdateForm form = new CartUpdateForm();
        form.setQuantity(setQuantityNum);
        form.setSelected(false);
        ResponseVo<CartVo> list = cartService.update(uid, productAddId, form);
        log.info("result:{}", gson.toJson(list));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), list.getStatus());

    }

//    @After
    public void delete(){
        log.info("【删除购物车】");
        ResponseVo<CartVo> list = cartService.delete(uid, productAddId);
        log.info("result:{}", gson.toJson(list));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), list.getStatus());

    }

    @Test
    public void selectAll(){
        ResponseVo<CartVo> list = cartService.selectAll(uid);
        log.info("result:{}", gson.toJson(list));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), list.getStatus());

    }

    @Test
    public void unSelectAll(){
        ResponseVo<CartVo> list = cartService.unSelectAll(uid);
        log.info("result:{}", gson.toJson(list));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), list.getStatus());

    }

    @Test
    public void sum(){
        ResponseVo<Integer> sum = cartService.sum(uid);
        log.info("result:{}", gson.toJson(sum));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), sum.getStatus());

    }
}