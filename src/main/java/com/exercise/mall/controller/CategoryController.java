package com.exercise.mall.controller;


import com.exercise.mall.service.ICategoryService;
import com.exercise.mall.vo.CategoryVo;
import com.exercise.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/categories")
    public ResponseVo<List<CategoryVo>> categories(){

        return categoryService.selectALl();
    }


}
