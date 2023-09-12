package com.exercise.mall.service;

import com.exercise.mall.MallApplicationTest;
import com.exercise.mall.enums.ResponseEnum;
import com.exercise.mall.vo.CategoryVo;
import com.exercise.mall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ICategoryServiceTest extends MallApplicationTest {

    @Autowired
    private ICategoryService categoryService;

    @Test
    public void selectALl() {
        ResponseVo<List<CategoryVo>> listResponseVo = categoryService.selectALl();
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), listResponseVo.getStatus());
    }
}