package com.exercise.mall.service;

import com.exercise.mall.MallApplicationTest;
import com.exercise.mall.enums.ResponseEnum;
import com.exercise.mall.form.ShippingForm;
import com.exercise.mall.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


@Slf4j
public class IShippingServiceTest extends MallApplicationTest {

    @Autowired
    private IShippingService shippingService;

    private Integer uid = 1;

    private Integer shippingId;

    private ShippingForm form;

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    @Before
    public void before() {
        ShippingForm form = new ShippingForm();
        form.setReceiverName("QS");
        form.setReceiverPhone("121");
        form.setReceiverMobile("1122021");
        form.setReceiverProvince("陕西");
        form.setReceiverCity("陕西市");
        form.setReceiverDistrict("临潼区");
        form.setReceiverAddress("斜口街道");
        form.setReceiverZip("100000");
        this.form = form;

        add();
    }



    public void add() {
        ResponseVo<Map<String, Integer>> responseVo = shippingService.add(uid, form);
        this.shippingId = responseVo.getData().get("shippingId");
        log.info("result:{}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());

    }

    @After
    public void delete() {
//        form.get
        ResponseVo responseVo = shippingService.delete(uid, shippingId);
        log.info("result:{}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());

    }

    @Test
    public void update() {
        form.setReceiverName("吾儿菲菲");
        ResponseVo responseVo = shippingService.update(uid, shippingId, form);
        log.info("updateResult:{}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void list() {

        ResponseVo<PageInfo> responseVo = shippingService.list(uid, pageNum, pageSize);
        log.info("listResult:{}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}