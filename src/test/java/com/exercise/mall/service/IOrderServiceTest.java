package com.exercise.mall.service;

import com.exercise.mall.MallApplicationTest;
import com.exercise.mall.consts.MallConst;
import com.exercise.mall.enums.ResponseEnum;
import com.exercise.mall.form.CartAddForm;
import com.exercise.mall.vo.CartVo;
import com.exercise.mall.vo.OrderVo;
import com.exercise.mall.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@Slf4j
@Transactional
public class IOrderServiceTest extends MallApplicationTest {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ICartService cartService;

    private Integer uid = 1;

    private Integer shippingId = 9;

    private Integer productAddId = 27;

    private Long orderId = 1695264524161L;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Before
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
    public void createTest() {
        ResponseVo<OrderVo> responseVo = create();
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    public ResponseVo<OrderVo> create(){

        ResponseVo<OrderVo> responseVo = orderService.create(uid, shippingId);
        log.info("result={}", gson.toJson(responseVo));
        return responseVo;
    }

    @Test
    public void list() {
        ResponseVo<PageInfo> responseVo = orderService.list(uid, 1, 10);
        log.info("resultList={}", gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());

    }

    @Test
    public void detail() {
        ResponseVo<OrderVo> vo = create();
        ResponseVo<OrderVo> responseVo = orderService.detail(uid, vo.getData().getOrderNo());
        log.info("resultDetail={}", gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());

    }
}