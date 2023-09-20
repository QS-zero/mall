package com.exercise.mall.service;

import com.exercise.mall.MallApplicationTest;
import com.exercise.mall.enums.ResponseEnum;
import com.exercise.mall.form.ShoppingForm;
import com.exercise.mall.pojo.Shipping;
import com.exercise.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.Assert.*;


@Slf4j
public class IShippingServiceTest extends MallApplicationTest {

    @Autowired
    private IShippingService shippingService;

    private Integer uid = 1;

    private Integer shippingId;

    @Test
    public void add() {
        ShoppingForm form = new ShoppingForm();
        form.setReceiverName("QS");
        form.setReceiverPhone("121");
        form.setReceiverMobile("1122021");
        form.setReceiverProvince("陕西");
        form.setReceiverCity("陕西市");
        form.setReceiverDistrict("临潼区");
        form.setReceiverAddress("斜口街道");
        form.setReceiverZip("100000");

        ResponseVo<Map<String, Integer>> add = shippingService.add(uid, form);
        shippingId = add.getData().get("shippingId");
        log.info("result:{}", add);
        log.info("shippingId:{}", shippingId);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), add.getStatus());

    }

    @Test
    public void delete() {

        ResponseVo responseVo = shippingService.delete(uid, 8);
        log.info("result:{}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());

    }

    @Test
    public void update() {
        ShoppingForm form = new ShoppingForm();
        form.setReceiverName("吾儿菲菲");
        ResponseVo responseVo = shippingService.update(uid, 7, form);
        log.info("upDateResult:{}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void list() {

    }
}