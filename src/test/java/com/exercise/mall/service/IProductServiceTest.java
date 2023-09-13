package com.exercise.mall.service;

import com.exercise.mall.MallApplicationTest;
import com.exercise.mall.enums.ResponseEnum;
import com.exercise.mall.vo.ProductDetailVo;
import com.exercise.mall.vo.ProductVo;
import com.exercise.mall.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class IProductServiceTest extends MallApplicationTest {

    @Autowired
    private IProductService productService;

    @Test
    public void list() {

        ResponseVo<PageInfo> listResponseVo = productService.list(null, 1, 2);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), listResponseVo.getStatus());
    }

    @Test
    public void detail(){
        ResponseVo<ProductDetailVo> detail = productService.detail(26);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), detail.getStatus());

    }
}