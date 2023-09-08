package com.exercise.mall.dao;

import com.exercise.mall.MallApplicationTests;
import com.exercise.mall.pojo.Category;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryMapperTest extends MallApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void findById() {
        Category byId = categoryMapper.findById(100001);
        System.out.println(byId.toString());
    }

    @Test
    public void queryById() {
        Category byId = categoryMapper.queryById(100001);
        System.out.println(byId.toString());
    }
}