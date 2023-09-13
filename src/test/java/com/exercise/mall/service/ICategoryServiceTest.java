package com.exercise.mall.service;

import com.exercise.mall.MallApplicationTest;
import com.exercise.mall.enums.ResponseEnum;
import com.exercise.mall.vo.CategoryVo;
import com.exercise.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class ICategoryServiceTest extends MallApplicationTest {

    @Autowired
    private ICategoryService categoryService;

    @Test
    public void selectALl() {
        ResponseVo<List<CategoryVo>> listResponseVo = categoryService.selectALl();
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), listResponseVo.getStatus());
    }

    @Test
    public void findSubCategoryId() {
        Set<Integer> set = new HashSet<>();
        categoryService.findSubCategoryId(100001, set);
        log.info("set{}", set);
    }
}